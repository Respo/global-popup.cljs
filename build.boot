
(set-env!
  :resource-paths #{"src"}
  :dependencies '[])

(def +version+ "0.1.1")

(deftask build []
  (comp
    (pom :project     'respo/global-popup
         :version     +version+
         :description "Global Popup for Respo"
         :url         "https://github.com/Respo/global-popup"
         :scm         {:url "https://github.com/Respo/global-popup"}
         :license     {"MIT" "http://opensource.org/licenses/mit-license.php"})
    (jar)
    (install)
    (target)))

(deftask deploy []
  (set-env!
    :repositories #(conj % ["clojars" {:url "https://clojars.org/repo/"}]))
  (comp
    (build)
    (push :repo "clojars" :gpg-sign (not (.endsWith +version+ "-SNAPSHOT")))))
