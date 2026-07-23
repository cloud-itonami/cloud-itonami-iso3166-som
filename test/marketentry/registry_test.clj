(ns marketentry.registry-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.registry :as registry]))

(deftest engagement-fee-recompute
  (let [e {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 860000.0}]
    (is (== 860000.0 (registry/compute-engagement-fee e)))
    (is (true? (registry/engagement-fee-matches-claim? e))))
  (let [bad {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 999000.0}]
    (is (false? (registry/engagement-fee-matches-claim? bad)))))

(deftest register-draft-and-submit
  (let [d (registry/register-draft "eng-1" "SOM" 0)
        s (registry/register-submit "eng-1" "SOM" 0)]
    (is (= "SOM-DFT-000000" (get d "draft_number")))
    (is (= "SOM-SUB-000000" (get s "submit_number")))
    (is (nil? (get-in d ["certificate" "proof"])))
    (is (= "draft-unsigned" (get-in s ["certificate" "status"])))))

(deftest register-requires-ids
  (is (thrown? Exception (registry/register-draft "" "SOM" 0)))
  (is (thrown? Exception (registry/register-submit "eng-1" "" 0))))

(deftest beneficial-owner-registration-required-by-ownership-pct
  (testing "strictly greater than 10% triggers the Regulation's own Beneficial Owner definition"
    (is (true? (registry/beneficial-owner-registration-required? {:beneficial-owner-pct 11})))
    (is (true? (registry/beneficial-owner-registration-required? {:beneficial-owner-pct 35})))
    (is (false? (registry/beneficial-owner-registration-required? {:beneficial-owner-pct 10})))
    (is (false? (registry/beneficial-owner-registration-required? {:beneficial-owner-pct 5})))
    (is (false? (registry/beneficial-owner-registration-required? {})))))

(deftest beneficial-owner-registration-required-by-control
  (testing "a declared control fact triggers the definition regardless of ownership percentage"
    (is (true? (registry/beneficial-owner-registration-required? {:beneficial-owner-pct 0 :beneficial-owner-control? true})))
    (is (true? (registry/beneficial-owner-registration-required? {:beneficial-owner-control? true})))))

(deftest beneficial-owner-registration-compliant-when-not-required
  (testing "an engagement below the threshold with no control fact is trivially compliant, disclosed or not"
    (is (true? (registry/beneficial-owner-registration-compliant? {:beneficial-owner-pct 5 :beneficial-owner-disclosed-within-deadline? false})))
    (is (true? (registry/beneficial-owner-registration-compliant? {:beneficial-owner-pct 10})))))

(deftest beneficial-owner-registration-compliant-when-disclosed
  (testing "a triggering engagement is compliant only when disclosed within the deadline"
    (is (true? (registry/beneficial-owner-registration-compliant? {:beneficial-owner-pct 35 :beneficial-owner-disclosed-within-deadline? true})))
    (is (false? (registry/beneficial-owner-registration-compliant? {:beneficial-owner-pct 35 :beneficial-owner-disclosed-within-deadline? false})))
    (is (false? (registry/beneficial-owner-registration-compliant? {:beneficial-owner-pct 35})))))

(deftest beneficial-owner-registration-noncompliant-claim-is-threshold-scoped
  (testing "an engagement that never triggers the Beneficial Owner definition is never flagged, even undisclosed"
    (is (false? (registry/beneficial-owner-registration-noncompliant-claim? {:beneficial-owner-pct 5 :beneficial-owner-disclosed-within-deadline? false}))))
  (testing "a triggering engagement that fails to disclose within the deadline -> noncompliant claim"
    (is (true? (registry/beneficial-owner-registration-noncompliant-claim? {:beneficial-owner-pct 35 :beneficial-owner-disclosed-within-deadline? false}))))
  (testing "a triggering engagement that DOES disclose within the deadline -> not flagged"
    (is (false? (registry/beneficial-owner-registration-noncompliant-claim? {:beneficial-owner-pct 35 :beneficial-owner-disclosed-within-deadline? true})))))

(deftest compute-beneficial-owner-penalty-accrues-only-when-noncompliant
  (testing "a compliant or non-triggering engagement always recomputes to zero, regardless of declared days"
    (is (== 0.0 (registry/compute-beneficial-owner-penalty {:beneficial-owner-pct 5 :beneficial-owner-noncompliant-days 40})))
    (is (== 0.0 (registry/compute-beneficial-owner-penalty {:beneficial-owner-pct 35 :beneficial-owner-disclosed-within-deadline? true :beneficial-owner-noncompliant-days 40}))))
  (testing "a noncompliant engagement recomputes $50/day x declared noncompliant days, per Art. 12(1)"
    (is (== 900.0 (registry/compute-beneficial-owner-penalty {:beneficial-owner-pct 35 :beneficial-owner-disclosed-within-deadline? false :beneficial-owner-noncompliant-days 18})))
    (is (== 0.0 (registry/compute-beneficial-owner-penalty {:beneficial-owner-pct 35 :beneficial-owner-disclosed-within-deadline? false})) "missing days defaults to zero, not an exception")))
