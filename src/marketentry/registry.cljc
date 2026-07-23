(ns marketentry.registry
  "Pure-function market-entry filing-draft + filing-submit record
  construction -- an append-only market-entry book-of-record draft.

  Like every sibling actor's registry, there is no single international
  reference-number standard for a public-procurement market-entry
  filing -- every jurisdiction assigns its own format. This namespace
  does NOT invent one; it builds a jurisdiction-scoped sequence number
  and validates the record's required fields, the same honest,
  non-fabricating discipline `marketentry.facts` uses.

  `engagement-fee-matches-claim?` is an HONEST reapplication of the
  SAME ground-truth-recompute DISCIPLINE sibling actors use (verify a
  claimed monetary total against the entity's own recorded quantity x
  unit fields), reapplied to a market-entry engagement fee line.

  `beneficial-owner-registration-compliant?` /
  `beneficial-owner-registration-noncompliant-claim?` /
  `compute-beneficial-owner-penalty` are the SAME discipline applied to
  a genuinely Somalia-specific mechanism this iteration found and read
  in full, directly: the Ministry of Commerce and Industry's
  **Beneficial Ownership Registration Regulation** (Xeer-Nidaamiyaha
  Diiwaangelinta Milkiilaha Dhabta ah ee Shirkadaha, July 2024). Its own
  Article 2(1) defines a Beneficial Owner as a natural person who holds
  MORE THAN 10% of a company's shares, OR has decision-making/
  management authority making them the de facto controller. Its own
  Article 3(2)-(3) and Article 5(1) require disclosure to the Registrar
  of Companies within THIRTY (30) WORKING DAYS of the triggering event.
  Its own Article 12(1) penalizes non-compliance with a fine of $50
  (fifty US dollars, or Somali Shilling equivalent) PER DAY, accruing
  from the date non-compliance began until the date of compliance,
  and/or suspension of the company's trade license -- this iteration
  read this Article's own text directly (own quote, translated from
  Somali): 'a fine ... $50 ... per day, starting from the date the
  non-compliance began until the date the company complies ...
  suspension of the trade license'.

  This is a GENUINELY DIFFERENT check SHAPE from the two sibling
  catalogs this iteration directly compared against before writing this
  one (Eritrea's Mining Law fixed-minimum-three-supplier-count RFO test,
  and South Sudan's Section 4(7)-(8) AND-of-three-independent-boolean-
  registration-facts test) -- both of those gate a PRE-DECLARED entity
  fact (`:mining-sector?` / `:external-beneficiary-procurement?`)
  against either a fixed count or a fixed set of booleans. This check
  instead DERIVES the obligation itself from a NUMERIC THRESHOLD
  comparison (is the declared ownership percentage > 10?) and, when
  triggered, gates on WHETHER A STATUTORY DEADLINE WAS MET (was
  disclosure made within 30 working days?) -- a
  THRESHOLD-TRIGGERED TIMELINESS-COMPLIANCE test, with an accompanying
  ground-truth PENALTY recompute (the $50/day accrual) that has no
  analogue in either sibling's flagship check. This iteration has not
  surveyed every sibling in the fleet, so this is stated as a
  comparison against the two catalogs actually read, not a fleet-wide
  uniqueness claim.

  SECTOR-SCOPED is the wrong word for this check (it applies to ANY
  engagement, not a declared sector) -- it is instead
  THRESHOLD-SCOPED: an engagement whose declared beneficial-owner
  ownership percentage is <= 10% AND has no declared control fact is
  never flagged, because the Regulation's own Article 2(1) definition
  of a Beneficial Owner simply does not reach it.

  This namespace is pure data + pure functions -- no I/O, no network
  call to any real registration portal. It builds the RECORD an
  operator would keep, not the act of submitting a portal registration
  itself (that is `marketentry.operation`'s `:filing/submit`, always
  human-gated -- see README Actuation)."
  (:require [clojure.string :as str]))

(defn- unsigned-certificate
  "Every certificate this actor produces is UNSIGNED -- signature is
  the market-entry operator's act, not this actor's."
  [kind subject record-id]
  {"@context" ["https://www.w3.org/ns/credentials/v2"]
   "type" ["VerifiableCredential" kind]
   "credentialSubject" {"id" subject "record" record-id}
   "proof" nil
   "issued_by_registry" false
   "status" "draft-unsigned"})

(defn- zero-pad [n w]
  (let [s (str n)]
    (str (apply str (repeat (max 0 (- w (count s))) "0")) s)))

(defn compute-engagement-fee
  "The ground-truth engagement fee for `engagement`'s own `:base-fee`
  and `:monitoring-months` x `:monthly-rate` -- a single flat
  base + months x rate calculation, not a full pricing engine."
  [{:keys [base-fee monthly-rate monitoring-months]}]
  (+ (double base-fee)
     (* (double monthly-rate) (double monitoring-months))))

(defn engagement-fee-matches-claim?
  "Does `engagement`'s own `:claimed-fee` equal the independently
  recomputed `compute-engagement-fee`?"
  [{:keys [claimed-fee] :as engagement}]
  (== (double claimed-fee) (compute-engagement-fee engagement)))

(def beneficial-owner-registration-thresholds
  "The Ministry of Commerce and Industry's Beneficial Ownership
  Registration Regulation (July 2024), read directly: the ownership
  percentage above which a natural person is a Beneficial Owner (Art.
  2(1)(a)), the number of working days within which disclosure to the
  Registrar of Companies is required (Art. 3(2)-(3)/5(1)), and the
  per-day accruing fine for non-compliance in US dollars (Art. 12(1))."
  {:ownership-threshold-pct 10
   :disclosure-deadline-working-days 30
   :daily-penalty-usd 50})

(defn beneficial-owner-registration-required?
  "Does `engagement`'s own declared beneficial-owner facts trigger the
  Regulation's own Art. 2(1) Beneficial Owner definition -- ownership
  strictly greater than the threshold percentage, OR a declared control
  fact (Art. 2(1)(a)'s 'has decision-making/management authority'
  branch)? A missing/nil `:beneficial-owner-pct` is treated as zero
  (does not throw)."
  [{:keys [beneficial-owner-pct beneficial-owner-control?]}]
  (boolean
   (or (> (double (or beneficial-owner-pct 0)) (:ownership-threshold-pct beneficial-owner-registration-thresholds))
       (boolean beneficial-owner-control?))))

(defn beneficial-owner-registration-compliant?
  "The ground-truth Art. 3/5 disclosure compliance for `engagement`,
  independently recomputed. When the Regulation's own Beneficial Owner
  definition is not triggered (see `beneficial-owner-registration-required?`),
  there is no disclosure obligation and the engagement is trivially
  compliant. When triggered, compliance requires the engagement's own
  declared `:beneficial-owner-disclosed-within-deadline?` to be true."
  [engagement]
  (boolean
   (or (not (beneficial-owner-registration-required? engagement))
       (true? (:beneficial-owner-disclosed-within-deadline? engagement)))))

(defn beneficial-owner-registration-noncompliant-claim?
  "Does `engagement` trigger the Regulation's own Beneficial Owner
  definition (`beneficial-owner-registration-required?`) while the
  INDEPENDENTLY recomputed `beneficial-owner-registration-compliant?`
  is false? An engagement whose declared ownership percentage does not
  exceed the threshold and declares no control fact is never flagged
  (threshold-scoped, see namespace docstring)."
  [engagement]
  (boolean (and (beneficial-owner-registration-required? engagement)
                (not (beneficial-owner-registration-compliant? engagement)))))

(defn compute-beneficial-owner-penalty
  "The ground-truth Art. 12(1) accruing daily fine, in US dollars, for
  `engagement`'s own declared `:beneficial-owner-noncompliant-days`
  (defaults to zero) -- ONLY accrues when the engagement is actually
  noncompliant per `beneficial-owner-registration-noncompliant-claim?`;
  a compliant or non-triggering engagement always recomputes to 0.0,
  regardless of what `:beneficial-owner-noncompliant-days` claims (the
  ground truth wins, the same discipline `engagement-fee-matches-claim?`
  uses)."
  [{:keys [beneficial-owner-noncompliant-days] :as engagement}]
  (if (beneficial-owner-registration-noncompliant-claim? engagement)
    (* (double (:daily-penalty-usd beneficial-owner-registration-thresholds))
       (double (max 0 (or beneficial-owner-noncompliant-days 0))))
    0.0))

(defn register-draft
  "Validate + construct the FILING-DRAFT registration DRAFT -- the
  market-entry operator's own act of preparing a portal registration
  package. Pure function -- does not touch any real procurement
  portal."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "draft: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "draft: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "draft: sequence must be >= 0" {})))
  (let [draft-number (str (str/upper-case jurisdiction) "-DFT-" (zero-pad sequence 6))
        record {"record_id" draft-number
                "kind" "filing-draft"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "draft_number" draft-number
     "certificate" (unsigned-certificate "FilingDraft" draft-number draft-number)}))

(defn register-submit
  "Validate + construct the FILING-SUBMIT registration DRAFT -- the
  market-entry operator's own act of actually submitting a portal
  registration (always human-gated upstream)."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "submit: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "submit: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "submit: sequence must be >= 0" {})))
  (let [submit-number (str (str/upper-case jurisdiction) "-SUB-" (zero-pad sequence 6))
        record {"record_id" submit-number
                "kind" "filing-submit"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "submit_number" submit-number
     "certificate" (unsigned-certificate "FilingSubmit" submit-number submit-number)}))

(defn append [history result]
  (conj (vec history) (get result "record")))
