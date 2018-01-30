
(defn read-password [guide]
  (String/valueOf (.readPassword (System/console) guide nil)))

(set-env!
  :resource-paths #{"src"}
  :dependencies '[]
  :repositories #(conj % ["clojars" {:url "https://clojars.org/repo/"
                                     :username "jiyinyiyong"
                                     :password (read-password "Clojars password: ")}]))

(def +version+ "0.1.3")

(deftask deploy []
  (comp
    (pom :project     'respo/global-popup
         :version     +version+
         :description "Global Popup for Respo"
         :url         "https://github.com/Respo/global-popup"
         :scm         {:url "https://github.com/Respo/global-popup"}
         :license     {"MIT" "http://opensource.org/licenses/mit-license.php"})
    (jar)
    (install)
    (push :repo "clojars" :gpg-sign false)))
