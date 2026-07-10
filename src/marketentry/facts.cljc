(ns marketentry.facts "Austria market-entry catalog.")
(def catalog
  {"AUT" {:name "Austria"
          :owner-authority "BBG / USP"
          :legal-basis "Bundesvergabegesetz (BVergG); EU directives"
          :national-spec "USP / e-procurement + Firmenbuch number"
          :provenance "https://www.usp.gv.at/"
          :required-evidence ["Firmenbuch number record"
                              "USP/e-procurement registration record"
                              "Firmenbuch extract"
                              "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / BBG"
          :rep-legal-basis "EU establishment or authorized representative for many procedures"
          :rep-provenance "https://www.usp.gv.at/"
          :corporate-number-owner-authority "Justiz / Firmenbuch"
          :corporate-number-legal-basis "Firmenbuchnummer"
          :corporate-number-provenance "https://www.justiz.gv.at/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR"
          :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "DEU" {:name "Germany" :owner-authority "e-Vergabe" :legal-basis "GWB/VgV"
          :national-spec "e-Vergabe" :provenance "https://www.evergabe-online.de/"
          :required-evidence ["Handelsregister extract" "e-Vergabe registration record" "USt-IdNr record" "Authorized-representative record"]}
   "CHE" {:name "Switzerland" :owner-authority "simap.ch" :legal-basis "BöB"
          :national-spec "simap.ch" :provenance "https://www.simap.ch/"
          :required-evidence ["UID record" "simap registration" "Commercial register extract" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
