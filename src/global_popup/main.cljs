
(ns global-popup.main
  (:require [respo.core :refer [render! clear-cache!]]
            [global-popup.comp.container :refer [comp-container]]
            [cljs.reader :refer [read-string]]
            [global-popup.updater.popup :as popup]))

(defn updater [store op op-data]
  (case
    op
    :popup/add
    (popup/add-one store op-data)
    :popup/drop
    (popup/drop-one store op-data)
    store))

(defonce store-ref (atom {}))

(defn dispatch! [op op-data]
  (println op op-data)
  (let [new-store (updater @store-ref op op-data)]
    (reset! store-ref new-store)))

(defonce states-ref (atom {}))

(defn render-app! []
  (let [target (.querySelector js/document "#app")]
    (render! (comp-container @store-ref) target dispatch! states-ref)))

(defn -main! []
  (enable-console-print!)
  (render-app!)
  (add-watch store-ref :changes render-app!)
  (add-watch states-ref :changes render-app!)
  (println "app started!"))

(defn on-jsload! []
  (clear-cache!)
  (render-app!)
  (println "code update."))

(set! (.-onload js/window) -main!)
