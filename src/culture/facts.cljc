(ns culture.facts
  "Country-level regional-culture catalog for Somalia (SOM) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"SOM"
   [{:culture/id "som.dish.bariis-iskukaris"
     :culture/name "Bariis iskukaris"
     :culture/country "SOM"
     :culture/kind :dish
     :culture/summary "Rice dish cooked in a single pan with meat, raisins, peas and the xawaash spice blend; the article states it is a national dish of Somalia and a staple almost universally served as part of a daily Somali meal."
     :culture/url "https://en.wikipedia.org/wiki/Bariis_iskukaris"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "som.dish.lahoh"
     :culture/name "Lahoh"
     :culture/name-local "Canjeero"
     :culture/country "SOM"
     :culture/kind :dish
     :culture/summary "Fermented spongy flatbread also called canjeero or canjeelo in Somalia, eaten regularly there and in Somaliland; a festive variant, cambaabur, is traditionally eaten at breakfast during Eid."
     :culture/url "https://en.wikipedia.org/wiki/Lahoh"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "som.dish.xalwo"
     :culture/name "Xalwo"
     :culture/country "SOM"
     :culture/kind :dish
     :culture/summary "Somali variant of halva, made from sugar, oil and cornstarch flavored with cardamom, nutmeg or cloves and sometimes peanuts; the article states it is known in Somalia as xalwo (also halwo or halwa)."
     :culture/url "https://en.wikipedia.org/wiki/Halva"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "som.beverage.camel-milk"
     :culture/name "Camel milk"
     :culture/country "SOM"
     :culture/kind :beverage
     :culture/summary "The article's production statistics show Somalia led world camel-milk production in 2017 with 953,673 tonnes, the highest global output that year."
     :culture/url "https://en.wikipedia.org/wiki/Camel_milk"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "som.product.frankincense"
     :culture/name "Frankincense"
     :culture/name-local "Maydi"
     :culture/country "SOM"
     :culture/kind :product
     :culture/summary "Boswellia frereana, native to northern Somalia (local names include yagcar and yagar), produces frankincense resin known locally as maydi or fooh and described in the article as \"the king of all frankincense\"; scientific evidence confirms the tree is endemic to Somalia."
     :culture/url "https://en.wikipedia.org/wiki/Boswellia_frereana"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "som.heritage.laas-geel"
     :culture/name "Laas Geel"
     :culture/country "SOM"
     :culture/kind :heritage
     :culture/summary "Cave rock-art site on the outskirts of Hargeisa in the Maroodi Jeex region, with paintings of domesticated cattle, herders, canines and giraffes estimated at 5,500 to 4,500 years old -- among the earliest known depictions of domesticated aurochs in the Horn of Africa; discovered by a French archaeological survey in 2002."
     :culture/url "https://en.wikipedia.org/wiki/Laas_Geel"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-som culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "SOM"))
                 " SOM entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
