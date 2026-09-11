(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest som-has-spec-basis
  (let [sb (facts/spec-basis "SOM")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/corporate-number-spec-basis "SOM")))
    (is (some? (facts/beneficial-owner-registration-spec-basis "SOM")))))

(deftest som-rep-spec-basis-is-honestly-absent
  (testing "no representative/director exclusion-extension provision could be found for Somalia -- deliberately not claimed"
    (is (nil? (facts/rep-spec-basis "SOM")))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "SOM")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "SOM" all)))
    (is (not (facts/required-evidence-satisfied? "SOM" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["SOM" "USA" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 2 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))

(deftest beneficial-owner-registration-spec-basis-criteria
  (let [bo (facts/beneficial-owner-registration-spec-basis "SOM")]
    (is (= 10 (get-in bo [:beneficial-owner-registration-criteria :ownership-threshold-pct])))
    (is (= 30 (get-in bo [:beneficial-owner-registration-criteria :disclosure-deadline-working-days])))
    (is (= 50 (get-in bo [:beneficial-owner-registration-criteria :daily-penalty-usd])))))
