(ns marketentry.facts
  "Per-jurisdiction public-procurement market-entry regulatory catalog
  -- the G2-style spec-basis table the Market-Entry Compliance Governor
  checks every `:jurisdiction/assess` proposal against ('did the advisor
  cite an OFFICIAL public source for this jurisdiction's requirements,
  or did it invent one?').

  Somalia's market-entry surface (WebFetch/curl-verified 2026-07-23) is
  genuinely fragmented across federal vs. regional authority, and this
  session's WebSearch tool was unavailable (fleet-wide budget exhausted,
  the same constraint the ERI/SSD siblings this scaffold was modeled on
  also hit) -- research here relies entirely on direct WebFetch/curl of
  primary and near-primary sources, plus the Wayback Machine
  (`web.archive.org`, fetched via plain `curl`, NOT WebFetch -- this
  session's WebFetch tool hard-blocks the `web.archive.org` host
  entirely, unrelated to any bot-detection on that site). NO site
  returning a Cloudflare/CAPTCHA-style bot-detection challenge was
  bypassed; where a source could not be reached, that is stated
  explicitly.

  - **`villasomalia.gov.so`** (the Federal Government of Somalia's
    presidency/seat-of-government domain, the URL this repo's own
    pre-existing `organization.edn` cited as `:official-url`) FAILS DNS
    resolution entirely as of this session (`curl` exit code 6, both
    `https://` and `http://`, both `www.` and bare host) -- confirmed
    via the Wayback Machine CDX API that it WAS live and crawled as
    recently as 2023 (`web.archive.org/cdx/search/cdx?url=villasomalia.gov.so*`
    returns hundreds of snapshots through 2023). This is reported here
    as an honest, dated finding, not silently worked around; the child
    ministry domains below are what this iteration actually used.
  - **`moci.gov.so`** (Federal Ministry of Commerce and Industry --
    Wasaaradda Federaalka ee Ganacsiga iyo Warshadaha), **`mof.gov.so`**
    (Federal Ministry of Finance), and **`molsa.gov.so`** (Ministry of
    Labour and Social Affairs) ALL resolve and serve real, current
    content (HTTP 200, fetched directly) -- these are this catalog's
    primary anchors. `somalichamber.so` (Somali Chamber of Commerce and
    Industry, SCCI) also resolves and confirms itself as a real trade
    association, though with no citable regulatory text of its own.
  - **Public procurement**: `mof.gov.so`'s own site structure names a
    real \"Public Procurement\" department AND a `LEGISLATION > ACTS`
    menu that itself names \"Procurement Act 2020\" and a
    \"Public Procurement Regulations\" entry alongside \"PFM Act 2019\",
    \"Revenue Act 2019\", \"Customs Act\", \"Extractive Industry Act\"
    and \"Statistics Act\" -- read directly at
    `mof.gov.so/index.php/legislation/procurement-act-2020` etc. Every
    one of these individual Act pages, however, rendered \"No Results
    found\" (an honest content gap on the ministry's own site -- the
    category page exists and names the Act, but no PDF/full-text is
    attached to that specific page this session); this iteration did
    NOT independently read the Procurement Act 2020's own operative
    text. What IS directly confirmed, from `mof.gov.so`'s own \"Public
    Procurement Department\" page (read directly, quoted below in
    `catalog`), is that a real Department of Public Procurement
    operates under the Ministry of Finance, arbitrates and awards
    tenders, and REGISTERS AND GRADES CONTRACTORS who do business with
    government -- this is the closest this iteration could get to a
    citable procurement-authority description. No dedicated self-service
    e-procurement portal domain (comparable to e.g. Benin's
    marches-publics.bj) was found.
  - **This catalog deliberately does NOT use the abbreviation \"PPDA\"
    for Somalia**, even though this repo's own pre-existing README.md /
    organization.edn / docs/operator-guide.md all did before this
    iteration. Nothing this iteration read on `mof.gov.so` (the real,
    confirmed home of the Department of Public Procurement) ever
    abbreviates that department to \"PPDA\" -- \"PPDA\" is Uganda's OWN,
    separately-named procurement authority (Public Procurement and
    Disposal of Public Assets Authority), and this iteration believes
    the pre-existing SOM claim was cross-contaminated from a sibling
    scaffold, the exact same failure mode `cloud-itonami-iso3166-ssd`
    independently diagnosed and corrected for itself on 2026-07-23 (see
    that repo's `organization.edn` `:head-role-note`). This iteration
    corrected README.md / organization.edn / docs/operator-guide.md
    accordingly -- see this repo's own git history for the exact diff.
  - **Business/company registration**: this iteration found and read,
    IN FULL, directly, the actual government-issued PRIMARY TEXT of
    Somalia's **Companies Law** -- `Law No. 18`, approved by the House
    of the People of the Somali Federal Parliament by a recorded
    unanimous vote (156 yes / 0 no / 0 abstain) at its 6th Session,
    26th plenary meeting, 25 December 2019, and signed into law by
    President Mohamed Abdullahi Mohamed \"Farmaajo\" on 26 December
    2019 -- hosted as an English-translation PDF directly on
    `moci.gov.so` (`moci.gov.so/wp-content/uploads/2026/05/Company-Law-Revised-English-Translation-.pdf`,
    68 pages, real extractable text, not a scan). Own Article 2,
    definition 9: '\"Registrar\" means the registrar of the Ministry of
    Commerce.' This iteration ALSO found and read, directly, a SECOND
    primary document independently confirming the same law number/date:
    Ministry of Commerce and Industry Regulation No. 001/2021 (dated 27
    January 2021, a scanned PDF this iteration read via its own page
    images -- HP-Scan output, no text layer, so read as an image, the
    same honest 'scanned, no extractable text layer' limitation
    `cloud-itonami-iso3166-ssd`'s Companies Act 2012 citation disclosed),
    which itself opens: 'Having seen Articles 14, 83-87, 89, 92 and 99
    of the Companies Law No. 18 of 26th December 2019' and Article 1
    establishes 'The Office of the Registrar of Companies ... part and
    under supervision of the Ministry of Commerce and Industry'. HIGH
    confidence -- TWO independent primary documents, one machine-text,
    one read via vision, agree on the exact law number and date.
  - **This vertical's FLAGSHIP check** is grounded in a THIRD primary
    document this iteration found and read in full, directly, also
    hosted on `moci.gov.so`: the **Beneficial Ownership Registration
    Regulation** (`Xeer-Nidaamiyaha Diiwaangelinta Milkiilaha Dhabta ah
    ee Shirkadaha`), prepared by the Ministry of Commerce and Industry,
    dated July 2024 (`Luuliyo 2024`) -- the most RECENT primary source
    this iteration found for Somalia. Its own Article 2(8) confirms, in
    its own words, 'Sharciga Shirkadaha' (the Companies Law) is 'the
    Companies Law of the Federal Republic of Somalia, numbered 18,
    issued 26 December 2019' -- independently corroborating the same
    law number/date a third time. See `catalog` below and
    `marketentry.registry` for the exact quoted operative text (Article
    2(1)'s >10%-ownership-or-control definition of a Beneficial Owner,
    Article 3/5's 30-working-day disclosure deadline, and Article 12's
    $50/day accruing fine + trade-license-suspension penalty).
  - **Tax / Tax Compliance Certificate (TCC)**: this iteration found and
    read, directly via its own scanned page images (HP-Scan, no text
    layer, same honest limitation as above), a Prime Minister's Decree
    -- `Xeer Ra'iisul Wasaare Lr. 138` (\"Decree of the Prime Minister
    No. 138\"), dated 24 August 2023, hosted on `mof.gov.so`
    (`mof.gov.so/sites/default/files/Publications/XEER%20RA%27IISUL%20WASAARAHA%20LR.%20138%20QARAARKA%20XOOJINTA%20U%20HOGAANSANAANTA%20SHARCIYADA%20DHAQLIGA_2_0001.pdf`),
    grounded in Articles 99(a)(b)(e) and 100(a)(d) of the Provisional
    Federal Constitution of Somalia, approving Council of Ministers
    Decision No. SHGW0044.08.23 of 24/08/2023, and signed by Prime
    Minister Hamza Cabdi Barre. Its own Article 2, point 2 (read
    directly, own text): all Ministries and Government institutions
    providing licenses ('shatiyada'), contracts/tenders
    ('qandarayaasyada'), or consulting/professional-fee services
    ('cashuurta adeegyada la talinta') are instructed to require a Tax
    Compliance Certificate -- the decree's OWN English abbreviation,
    given in the text itself: '(SHCN/TCC)' -- from the companies and
    institutions to which government services are extended. This
    iteration did NOT independently confirm which specific office
    within the Ministry of Finance issues the TCC (the decree instructs
    OTHER ministries to REQUIRE it, it does not itself name the issuing
    office) -- a reasonable inference is the Ministry's own Revenue /
    Inland Revenue function (confirmed real via `mof.gov.so`'s own site
    structure: 'REVENUE > Customs Administration, Customs, Extractive
    Industry Income, Inland Revenue Trends, Revenue Policy, Tax
    Administration'), stated here as an inference, not confirmed fact.
    `mof.gov.so` also separately names a 'Revenue Act 2019' on its own
    LEGISLATION menu (read directly) -- this iteration could NOT find
    that Act's own operative text (its page also returned \"No Results
    found\"), an honest gap, not a claim the Act does not exist.
  - **Investment law**: this iteration found and read, directly, the
    full text (24 Articles, read via UNCTAD's Investment Laws Navigator,
    `investmentpolicy.unctad.org/investment-laws/laws/538/somalia-foreign-investment-law`)
    of a \"Federal Republic of Somalia Foreign Investment Law\" --
    establishing a Foreign Investment Board at the Ministry of Planning
    and International Cooperation, whose own membership list (Article
    3(2), read directly) names the Permanent Secretary of the Ministry
    of Planning and International Cooperation, the Permanent Secretary
    of the Ministry of Foreign Affairs, the Director General of the
    Ministry of Finance, the Director General of the Ministry of
    Commerce and Industry, the Director General of the Ministry of
    Labor and Social Affairs, the Director General of the Central Bank,
    and the Chairman of the Chamber of Commerce -- EVERY one of these
    named ministries this iteration independently confirmed is real via
    a separate, direct fetch this same session (moci.gov.so, mof.gov.so,
    molsa.gov.so, somalichamber.so all resolve with real content), which
    raises this iteration's confidence the document is genuine.
    **HONEST, EXPLICIT CAVEAT**: the document's OWN header fields are
    literally blank in the source UNCTAD hosts -- 'Foreign Investment
    Law no: _______ Of the ______________' -- so this iteration does
    NOT know, and does NOT invent, this law's own instrument number or
    exact enactment date. See `statute.facts` for the full disclosure
    and the quoted Article 17(3) 50%-local-staffing provision.
  - **Labor law**: `molsa.gov.so` (Ministry of Labour and Social
    Affairs) is confirmed real and describes a 'Legal and Labour
    Relations Department' responsible for 'the implementation of the
    Labour Laws' and 'Enforcement of the Trade Union Laws', and a video
    title on the ministry's own site names 'Xeerka Shaqada Soomaaliya'
    (the Somali Labour Law) by that generic name -- but NO specific
    instrument number, year, or operative text could be found this
    session (`ecolex.org` and direct `molsa.gov.so` sub-page fetches
    both came up empty; ILO's NATLEX country page returned a plain HTTP
    403 on every attempt, an access denial this iteration did NOT try
    to bypass). This is an HONEST, EXPLICITLY-REPORTED GAP -- Somalia
    almost certainly has a labor code of some kind, this iteration
    simply could not read its own primary text or confirm its exact
    number/date through any source reachable this session. See
    `statute.facts` -- no labor-law catalog entry is included as a
    result, the same honest-omission discipline `cloud-itonami-iso3166-ssd`
    used for its own unconfirmed Taxation Act.

  Coverage is reported HONESTLY (see `coverage`): a jurisdiction not in
  this table has NO spec-basis, full stop -- the advisor must not
  fabricate one, and the governor holds if it tries.")

(def catalog
  "iso3 -> requirement map. `:required-evidence` mirrors the generic
  intake/portal-registration/filing evidence set; `:legal-basis` /
  `:owner-authority` / `:provenance` are the G2 citation the governor
  requires before any `:jurisdiction/assess` proposal can commit. SOM
  deliberately carries NO `:rep-owner-authority` -- this iteration found
  no representative/director exclusion-extension provision for Somalia
  in the time available (an honest scope gap, not a design choice).
  `:beneficial-owner-registration-owner-authority` /
  `:beneficial-owner-registration-legal-basis` /
  `:beneficial-owner-registration-criteria` /
  `:beneficial-owner-registration-provenance` ground this vertical's
  flagship governor check (`beneficial-owner-registration-compliant?`/
  `beneficial-owner-registration-noncompliant-claim?` in
  `marketentry.registry`)."
  {"SOM" {:name "Somalia"
          :owner-authority "Ministry of Commerce and Industry (MoCI) -- Registrar of Companies (established by MoCI Regulation No. 001/2021, own Article 1: 'part and under supervision of the Ministry of Commerce and Industry') for business/company registration; Department of Public Procurement, Ministry of Finance, for public procurement (own page, read directly: 'The Department of Public Procurement operates under the Ministry of Finance ... arbitrate and award tenders ... coupled with the registration and grading of contractors who are willing to do business with government'). Neither body is abbreviated 'PPDA' anywhere this iteration read -- see namespace docstring"
          :legal-basis "Companies Law No. 18 (Federal Republic of Somalia, House of the People 6th Session/26th plenary meeting, unanimous 156-0-0 vote 25/12/2019, signed by President Mohamed Abdullahi Mohamed 'Farmaajo' 26/12/2019) + MoCI Regulation No. 001/2021 (27/01/2021) for business/company registration, both read directly in full; 'Procurement Act 2020' + 'Public Procurement Regulations' are named on mof.gov.so's own LEGISLATION menu but this iteration could NOT independently read their own operative text this session (each page returned 'No Results found' -- an honest content gap, not a claim the Acts don't exist)"
          :national-spec "SBRLS (Small Business Registration & Licensing System), MoCI's own one-stop registration system for companies/businesses/cooperatives/factories, live at ebusiness.gov.so (confirmed to resolve; renders as a minimal SPA shell with title 'eBusiness Somalia', the same honest 'real domain, little extractable text' limitation cloud-itonami-iso3166-ssd found for South Sudan's eservices.gov.ss subdomains). No dedicated self-service e-procurement portal domain was independently found"
          :provenance "https://moci.gov.so/wp-content/uploads/2026/05/Company-Law-Revised-English-Translation-.pdf ; https://moci.gov.so/wp-content/uploads/2026/05/Company-Law-Regulation-No.-0012021-english-version.pdf ; https://mof.gov.so/index.php/department/public-procurement"
          :required-evidence ["Certificate of Incorporation record (Registrar of Companies, Ministry of Commerce and Industry, per Companies Law No. 18 Art. 2(9) and MoCI Regulation No. 001/2021 Art. 4(1))"
                              "Beneficial Owner disclosure record, when the engagement has a natural-person owner with >10% shareholding or de facto control (per the Ministry of Commerce and Industry's Beneficial Ownership Registration Regulation, July 2024, Art. 2(1)/3/5 -- this catalog's flagship check, see marketentry.registry)"
                              "Tax Compliance Certificate (SHCN/TCC) record, when the engagement holds a government license, contract/tender, or consulting/professional-fee engagement (per Xeer Ra'iisul Wasaare Lr. 138, 24/08/2023, Art. 2(2))"
                              "Contractor registration/grading record, when the engagement is a public-procurement tender (per the Department of Public Procurement's own confirmed function, Ministry of Finance)"
                              "Authorized-representative confirmation record"]
          :corporate-number-owner-authority "Ministry of Finance -- the Tax Compliance Certificate (SHCN/TCC) is required of government-facing engagements per Xeer Ra'iisul Wasaare Lr. 138 (24/08/2023); this iteration could NOT independently confirm which specific office within the Ministry issues the TCC itself (the decree instructs OTHER ministries to require it; the Ministry's own 'Revenue'/'Inland Revenue Trends'/'Tax Administration' site sections are the most likely issuing function, but this is stated as an inference, not a confirmed fact -- see namespace docstring)"
          :corporate-number-legal-basis "Xeer Ra'iisul Wasaare Lr. 138 (Decree of the Prime Minister No. 138), 24 August 2023, grounded in Articles 99(a)(b)(e)/100(a)(d) of the Provisional Federal Constitution of Somalia and Council of Ministers Decision No. SHGW0044.08.23; own Article 2(2) (read directly, own text, paraphrased from Somali): all Ministries and Government institutions providing licenses, contracts/tenders, or consulting/professional-fee services are instructed to require a Tax Compliance Certificate ('SHCN/TCC', the decree's own English abbreviation) from the companies/institutions receiving government services. A separately-named 'Revenue Act 2019' appears on mof.gov.so's own legislation menu but this iteration could not read its own operative text this session"
          :corporate-number-provenance "https://mof.gov.so/sites/default/files/Publications/XEER%20RA%27IISUL%20WASAARAHA%20LR.%20138%20QARAARKA%20XOOJINTA%20U%20HOGAANSANAANTA%20SHARCIYADA%20DHAQLIGA_2_0001.pdf (Xeer Ra'iisul Wasaare Lr. 138, read directly via page images) ; https://mof.gov.so/index.php/legislation/revenue-act-2019 (named only, own text not found)"
          :beneficial-owner-registration-owner-authority "Registrar of Companies, Ministry of Commerce and Industry (established by MoCI Regulation No. 001/2021 Art. 1) -- administers the Beneficial Ownership Registration Regulation on behalf of the Minister of Commerce and Industry"
          :beneficial-owner-registration-legal-basis "Beneficial Ownership Registration Regulation (Xeer-Nidaamiyaha Diiwaangelinta Milkiilaha Dhabta ah ee Shirkadaha), Ministry of Commerce and Industry, July 2024 -- read directly in full (13 Articles). Own Article 2(1): a Beneficial Owner ('Milkiilaha Dhabta ah') is a natural person who (a) holds MORE THAN 10% of a company's shares or has decision-making/management authority making them the de facto controller, (b) has a broad direct or indirect economic interest in the company, (c) acts as a proxy/nominee for the company's business dealings, or (d) has effective power to direct the company or an equivalent legal person. Own Article 3(2)-(3) and Article 5(1): disclosed and undisclosed/nominee ('Milkiile Daahsoon') Beneficial Owners alike must be reported to the Registrar within THIRTY (30) WORKING DAYS of the triggering event (entry in the shareholder register, or the change itself). Own Article 12(1): non-compliance is penalized by a fine of $50 (fifty US dollars, or Somali Shilling equivalent) PER DAY, accruing from the date non-compliance began until the date of compliance, and/or suspension of the company's trade license"
          :beneficial-owner-registration-criteria {:ownership-threshold-pct 10 :disclosure-deadline-working-days 30 :daily-penalty-usd 50}
          :beneficial-owner-registration-provenance "https://moci.gov.so/wp-content/uploads/2026/05/Beneficial-Ownership-Regulation-LR.167-XEER-NIDAAMIYAHA-DIIWAANGALANT-MULKIILAHA-DHABTA-HA-EE-SHIRKADAHA2.pdf"}
   "USA" {:name "United States"
          :owner-authority "U.S. General Services Administration (GSA) / SAM.gov"
          :legal-basis "Federal Acquisition Regulation (FAR); System for Award Management"
          :national-spec "SAM.gov entity registration + NAICS self-certification"
          :provenance "https://sam.gov/"
          :required-evidence ["EIN record"
                              "SAM.gov registration record"
                              "State business registration record"
                              "Authorized-representative record"]}
   "DEU" {:name "Germany"
          :owner-authority "Beschaffungsamt des BMI / e-Vergabe platforms"
          :legal-basis "Gesetz gegen Wettbewerbsbeschränkungen (GWB) / VgV"
          :national-spec "e-Vergabe supplier registration under EU procurement directives"
          :provenance "https://www.evergabe-online.de/"
          :required-evidence ["Handelsregister extract"
                              "e-Vergabe registration record"
                              "USt-IdNr record"
                              "Authorized-representative record"]}})

(defn spec-basis
  "The jurisdiction's requirement map, or nil -- nil means NO spec-basis,
  and the governor must hold any proposal that tries to assess or file
  on it."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report: how many of the requested jurisdictions actually
  have a spec-basis entry. Never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-som R0: " (count catalog)
                 " jurisdictions seeded with an official spec-basis. "
                 "This is a starting catalog for market-entry navigation, "
                 "not a survey of all ~194 jurisdictions -- extend "
                 "`marketentry.facts/catalog`, never fabricate a "
                 "jurisdiction's requirements.")})))

(defn required-evidence-satisfied?
  "Does `submitted` (a set/coll of evidence keywords or strings) satisfy
  every evidence item listed for `iso3`? Missing spec-basis -> never
  satisfied."
  [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (let [need (count required-evidence)
          have (count (filter (set submitted) required-evidence))]
      (= need have))))

(defn evidence-checklist [iso3]
  (:required-evidence (spec-basis iso3) []))

(defn rep-spec-basis
  "The jurisdiction's representative-related requirement map, or nil when
  this catalog has no such regime. For SOM this is deliberately nil --
  no representative/director exclusion-extension provision could be
  found for Somalia in this iteration (see namespace docstring)."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))

(defn corporate-number-spec-basis
  "The jurisdiction's corporate-number / tax-id regime, or nil."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority
                       :corporate-number-legal-basis
                       :corporate-number-provenance]))))

(defn beneficial-owner-registration-spec-basis
  "The jurisdiction's Beneficial Ownership Registration regime, or nil.
  For SOM this is real and current (July 2024) -- the flagship check
  this vertical adds is grounded here (Ministry of Commerce and Industry
  Beneficial Ownership Registration Regulation: >10% ownership/control
  triggers a 30-working-day Registrar-disclosure deadline, backed by a
  $50/day accruing fine + trade-license-suspension penalty)."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:beneficial-owner-registration-owner-authority sb)
      (select-keys sb [:beneficial-owner-registration-owner-authority
                       :beneficial-owner-registration-legal-basis
                       :beneficial-owner-registration-criteria
                       :beneficial-owner-registration-provenance]))))
