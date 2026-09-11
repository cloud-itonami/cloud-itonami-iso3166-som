(ns statute.facts-test
  (:require [kotoba.lang.text :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest som-has-spec-basis
  (let [sb (facts/spec-basis "SOM")]
    (is (= 6 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://") sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["SOM" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= #{"som.companies-law-18-2019" "som.moci-regulation-001-2021" "som.foreign-investment-law"}
         (set (mapv :statute/id (facts/by-topic "SOM" :incorporation)))))
  (is (= ["som.beneficial-ownership-regulation-2024"]
         (mapv :statute/id (facts/by-topic "SOM" :beneficial-ownership))))
  (is (= #{"som.tcc-decree-138-2023" "som.revenue-act-2019"}
         (set (mapv :statute/id (facts/by-topic "SOM" :taxation)))))
  (is (= ["som.foreign-investment-law"]
         (mapv :statute/id (facts/by-topic "SOM" :investment))))
  (is (empty? (facts/by-topic "SOM" :labor))
      "no Somali labor-law instrument number/date/primary-text could be independently verified this iteration -- honestly absent, see namespace docstring")
  (is (empty? (facts/by-topic "ATL" :labor))))
