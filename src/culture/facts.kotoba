(ns culture.facts
  "Country-level regional-culture catalog for Austria (AUT) -- national
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
  {"AUT"
   [{:culture/id "aut.dish.wiener-schnitzel"
     :culture/name "Wiener schnitzel"
     :culture/country "AUT"
     :culture/kind :dish
     :culture/summary "Thin, breaded, pan-fried veal cutlet, one of the national dishes of Austria."
     :culture/url "https://en.wikipedia.org/wiki/Wiener_schnitzel"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aut.dish.sachertorte"
     :culture/name "Sachertorte"
     :culture/country "AUT"
     :culture/kind :dish
     :culture/summary "Austrian chocolate cake of sponge, apricot jam and chocolate glaze, created by Franz Sacher and closely identified with Vienna."
     :culture/url "https://en.wikipedia.org/wiki/Sachertorte"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aut.dish.apfelstrudel"
     :culture/name "Apple strudel"
     :culture/name-local "Apfelstrudel"
     :culture/country "AUT"
     :culture/kind :dish
     :culture/summary "Traditional Viennese pastry of thin strudel dough with spiced apple filling, considered a national dish of Austria along with Wiener schnitzel and Tafelspitz."
     :culture/url "https://en.wikipedia.org/wiki/Apfelstrudel"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aut.dish.tafelspitz"
     :culture/name "Tafelspitz"
     :culture/country "AUT"
     :culture/kind :dish
     :culture/summary "Classic dish of Viennese cuisine of boiled veal or beef in broth with minced apples and horseradish, popular in all of Austria."
     :culture/url "https://en.wikipedia.org/wiki/Tafelspitz"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aut.beverage.gruner-veltliner"
     :culture/name "Grüner Veltliner"
     :culture/country "AUT"
     :culture/kind :beverage
     :culture/summary "White wine grape variety native to Austria and the country's most-planted grape, renowned in Danube regions such as the Wachau."
     :culture/url "https://en.wikipedia.org/wiki/Gr%C3%BCner_Veltliner"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aut.beverage.almdudler"
     :culture/name "Almdudler"
     :culture/country "AUT"
     :culture/kind :beverage
     :culture/summary "Austrian herbal carbonated soft drink produced since 1957, called the national drink of Austria."
     :culture/url "https://en.wikipedia.org/wiki/Almdudler"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aut.craft.vienna-porcelain"
     :culture/name "Vienna porcelain"
     :culture/country "AUT"
     :culture/kind :craft
     :culture/summary "Hard-paste porcelain of the Vienna Porcelain Manufactory (1718–1864), Europe's second-oldest porcelain factory after Meissen."
     :culture/url "https://en.wikipedia.org/wiki/Vienna_porcelain"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aut.festival.salzburg-festival"
     :culture/name "Salzburg Festival"
     :culture/name-local "Salzburger Festspiele"
     :culture/country "AUT"
     :culture/kind :festival
     :culture/summary "Annual summer music and drama festival held in Salzburg since 1920, emphasizing the works of Mozart."
     :culture/url "https://en.wikipedia.org/wiki/Salzburg_Festival"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "aut.heritage.schonbrunn"
     :culture/name "Schönbrunn Palace"
     :culture/name-local "Schloss Schönbrunn"
     :culture/country "AUT"
     :culture/kind :heritage
     :culture/summary "Baroque former summer residence of the Habsburg rulers in Vienna with 1,441 rooms, a UNESCO World Heritage Site since 1996."
     :culture/url "https://en.wikipedia.org/wiki/Sch%C3%B6nbrunn_Palace"
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
      :note (str "cloud-itonami-iso3166-aut culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "AUT"))
                 " AUT entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
