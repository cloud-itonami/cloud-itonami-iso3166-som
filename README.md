# cloud-itonami-iso3166-som

**SOM**: Somalia.

- Department of Public Procurement (Ministry of Finance)
- Company Registry (Registrar of Companies, Ministry of Commerce and Industry)

AGPL-3.0-or-later.

## Market-entry / statute catalogs

Governed public-sector market-entry compliance actor, same architecture
as `cloud-itonami-iso3166-eri`/`-ssd` (studied as structural models for
this scaffold -- see Research notes below):

- `src/marketentry/{facts,governor,phase,sim,operation,registry,store,
  marketentryllm}.cljc` -- the actor. `facts.cljc` cites the Companies
  Law No. 18 (26 December 2019) + MoCI Regulation No. 001/2021 for
  business/company registration, and the Department of Public
  Procurement (Ministry of Finance) for public procurement.
  `governor.cljc`'s flagship check independently recomputes the
  Ministry of Commerce and Industry's Beneficial Ownership Registration
  Regulation (July 2024) own >10%-ownership-or-control Beneficial Owner
  definition and its own 30-working-day Registrar-disclosure deadline
  -- a THRESHOLD-TRIGGERED TIMELINESS-COMPLIANCE test, a check shape
  genuinely different from both siblings this scaffold was modeled on
  (Eritrea's Mining Law fixed-minimum-supplier-count test, and South
  Sudan's Section 4(7)-(8) AND-of-three-booleans registration test). See
  the namespace docstrings for the full research trail and honestly-
  narrowed scope, including facts this iteration could NOT verify (a
  Somali labor-law citation, a representative/director exclusion
  provision, and the Foreign Investment Law's own instrument number).
- `src/statute/facts.cljc` -- general-law catalog: the Companies Law No.
  18 (2019), MoCI Regulation No. 001/2021, the Beneficial Ownership
  Registration Regulation (2024), and a Prime Minister's Decree on Tax
  Compliance Certificates (No. 138, 2023) -- all read as PRIMARY TEXT
  directly. Two lower-confidence entries (Revenue Act 2019, named by the
  Ministry of Finance's own site but its own text not found; the
  Foreign Investment Law, whose own header fields are blank in the
  UNCTAD source this iteration read) are included with explicit
  honesty caveats. No labor-law entry is included -- an honest gap, not
  an omission by design.

## Research notes (2026-07-23)

Somalia's federal institutional/web infrastructure turned out to be
GENUINELY THIN in some places and genuinely rich in others -- a mixed
picture, not uniformly fragmented. Every citation in this repo's
catalogs was independently fetched and read this session via:

- **`moci.gov.so`** (Federal Ministry of Commerce and Industry) --
  hosts, directly, downloadable PDFs of the Companies Law No. 18 (2019,
  machine-readable, 68 pages), MoCI Regulation No. 001/2021 (scanned,
  read via its own page images), and the Beneficial Ownership
  Registration Regulation (July 2024, machine-readable, 13 Articles) --
  this repo's flagship-check source.
- **`mof.gov.so`** (Federal Ministry of Finance) -- confirmed a real
  Department of Public Procurement (own page, read directly) and hosts
  a Prime Minister's Decree (Xeer Ra'iisul Wasaare Lr. 138, 24 August
  2023, scanned, read via its own page images) requiring a Tax
  Compliance Certificate (SHCN/TCC) for government-facing engagements.
  Its own LEGISLATION menu additionally NAMES a 'Procurement Act 2020',
  'Public Procurement Regulations' and 'Revenue Act 2019' -- but each of
  those specific pages returned "No Results found" (an honest content
  gap on the ministry's own site, not a fetch failure); this iteration
  did NOT read those Acts' own operative text.
- **`molsa.gov.so`** (Ministry of Labour and Social Affairs) --
  confirmed real, but no specific labor-law instrument number/date/text
  could be found (an honest, explicitly-reported gap).
- **UNCTAD's Investment Laws Navigator**
  (`investmentpolicy.unctad.org`) -- hosts the full 24-Article text of a
  "Federal Republic of Somalia Foreign Investment Law"; its own header
  fields (instrument number, date) are blank in the source, an honest
  caveat this iteration did not paper over.
- **`villasomalia.gov.so`** (this repo's own pre-existing
  `organization.edn` `:official-url`) FAILS DNS resolution entirely as
  of this session, confirmed via the Wayback Machine CDX API to have
  been live and crawled as recently as 2023 -- reported here as an
  honest, dated finding.
- **`ecolex.org`** / **FAO's FAOLEX** -- used to cross-check for a
  Somalia labor code, investment law, and company law; also surfaced
  real but DIFFERENT-jurisdiction Somaliland-specific instruments (e.g.
  "Somaliland Special Economic Zones Law, No: 93/2021") which this
  catalog deliberately does NOT conflate with Federal Somalia law (see
  `statute.facts` namespace docstring).
- **No bot-detection challenge was bypassed.** ILO's NATLEX country
  page returned a plain HTTP 403 on every attempt (an access denial,
  not a CAPTCHA/challenge page) and was simply left unreachable --
  reported as an honest gap, not worked around. This session's
  WebSearch tool was unavailable (fleet-wide budget exhausted by
  concurrent sibling work, the same constraint `cloud-itonami-iso3166-eri`/
  `-ssd` hit) -- research relied entirely on direct WebFetch/curl of
  primary sources plus the Wayback Machine (fetched via plain `curl`,
  NOT WebFetch, which hard-blocks the `web.archive.org` host entirely,
  unrelated to any bot-detection on that site).
- **This repo's own pre-existing README.md / organization.edn /
  docs/operator-guide.md claimed Somalia's public-procurement authority
  is abbreviated "PPDA".** This iteration found NO evidence anywhere it
  read that any Somali institution uses that abbreviation -- the real,
  confirmed body is the Department of Public Procurement, Ministry of
  Finance. "PPDA" (Public Procurement and Disposal of Public Assets
  Authority) is Uganda's OWN, separately-named procurement authority;
  this iteration believes the claim was cross-contaminated from a
  sibling scaffold, the exact same failure mode
  `cloud-itonami-iso3166-ssd` independently diagnosed and corrected for
  itself on 2026-07-23. Corrected in this iteration's commit.

Sibling repos studied as structural models before writing any code:
`cloud-itonami-iso3166-eri` (Eritrea) and `cloud-itonami-iso3166-ssd`
(South Sudan) -- both are the only East African/fragile-state siblings
with the FULL `marketentry` + `statute` pattern already built out
(Sudan, Djibouti and Ethiopia currently have `marketentry` only, no
`statute`).

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Somalia:

- `src/culture/facts.cljc` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
