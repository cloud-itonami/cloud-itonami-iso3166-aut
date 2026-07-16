(ns statute.facts
  "General-law compliance catalog for Austria (AUT) -- a 43rd
  country-level entry (see cloud-itonami-iso3166-jpn/-usa/-gbr/-deu/-fra/
  -can/-aus/-kor/-nld/-ita/-esp/-swe/-nor/-dnk/-fin/-prt/-bel/-bra/-mex/
  -chl/-arg/-zaf/-col/-ury/-cri/-pan/-ecu/-pry/-gtm/-hnd/-ind/-ken/-tha/
  -are/-vnm/-idn/-phl/-egy/-tur/-nga/-sau/-mys for the first forty-two)
  per ADR-2607141700 (cloud-itonami-compliance-fact-federation).

  Reuses this tick-window's already-verified capital-status finding
  from cloud-itonami-municipality-aut-vienna (tick 125): Vienna is
  Austria's stable capital, with no ongoing ambiguity.

  Austria's official federal legal-information system (ris.bka.gv.at)
  returned HTTP 503 Service Unavailable on multiple attempts across
  this tick and the prior one (tick 125's Vienna entries), so this
  catalog's entries instead cite alternate legal-publisher and
  official-regulator domains.

  Gesetz vom 6. März 1906 über Gesellschaften mit beschränkter Haftung
  (GmbH-Gesetz, RGBl. 1906/58) -- title, law number, and date directly
  confirmed by reading a preview excerpt hosted by MANZ (a
  well-established Austrian legal publisher, manz.at) via the
  Read-tool saved-path fallback (WebFetch itself reported the PDF as
  illegible/binary; the metadata title 'GmbHG_Feltl_Titelei' matched a
  legal commentary book, not the law text itself, until the rendered
  page confirmed the actual citation). One of the world's earliest
  GmbH-type statutes.

  Datenschutzgesetz (DSG, Federal Act concerning the Protection of
  Personal Data) -- directly confirmed via
  data-protection-authority.gv.at (Austria's Data Protection
  Authority, the official regulator's own government domain), which
  states verbatim: 'The Federal Act concerning the Protection of
  Personal Data (Datenschutzgesetz - DSG) has entered into force on
  25 May 2018.'

  An entry not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url/date.")

(def catalog
  "ISO3166 alpha-3 -> vector of statute entries."
  {"AUT"
   [{:statute/id "aut.rgbl-58-1906-gmbh-gesetz"
     :statute/title "Gesetz vom 6. März 1906 über Gesellschaften mit beschränkter Haftung (GmbH-Gesetz)"
     :statute/jurisdiction "AUT"
     :statute/kind :law
     :statute/law-number "RGBl. 1906/58"
     :statute/url "https://api.shop.manz.at/upload/text/9783214186104/9783214186104_23_01.pdf"
     :statute/url-provenance :manz-at-legal-publisher
     :statute/enacted-date "1906-03-06"
     :statute/retrieved-at "2026-07-17"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "aut.dsg-2018-datenschutzgesetz"
     :statute/title "Datenschutzgesetz (Federal Act concerning the Protection of Personal Data)"
     :statute/jurisdiction "AUT"
     :statute/kind :law
     :statute/law-number "DSG"
     :statute/url "https://data-protection-authority.gv.at/data-protection-laws/relevant-data-protection-laws"
     :statute/url-provenance :official-data-protection-authority-gv-at
     :statute/enacted-date "2018-05-25"
     :statute/retrieved-at "2026-07-17"
     :statute/topic #{:data-protection :privacy}}]})

(defn spec-basis [jurisdiction] (get catalog jurisdiction))

(defn coverage
  ([] (coverage (keys catalog)))
  ([jurisdictions]
   (let [have (filter catalog jurisdictions)
         missing (remove catalog jurisdictions)]
     {:requested (count jurisdictions)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-aut statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "AUT")) " Austria entries seeded "
                 "with manz.at/data-protection-authority.gv.at citations. "
                 "Extend `statute.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [jurisdiction topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis jurisdiction)))
