
(ns global-popup.main
  (:require [respo.core :refer [render! clear-cache! realize-ssr!]]
            [respo.cursor :refer [mutate]]
            [global-popup.comp.container :refer [comp-container]]
            [cljs.reader :refer [read-string]]
            [global-popup.updater.popup :as popup]
            [global-popup.schema :as schema]
            [global-popup.util :refer [gen-id!]]
            [reel.util :refer [id!]]
            [reel.core :refer [reel-updater refresh-reel listen-devtools!]]
            [reel.schema :as reel-schema]))

(def ssr? (some? (js/document.querySelector "meta.respo-ssr")))

(defn updater [store op op-data op-id]
  (case op
    :states (update store :states (mutate op-data))
    :popup/add (popup/add-one store op-data op-id)
    :popup/drop (popup/drop-one store op-data op-id)
    :popup/clear-float (popup/clear-float store op-data op-id)
    store))

(def *reel
  (atom (-> reel-schema/reel (assoc :base schema/store) (assoc :store schema/store))))

(defn dispatch! [op op-data]
  (let [op-id (id!), next-reel (reel-updater updater @*reel op op-data op-id)]
    (reset! *reel next-reel)))

(def mount-target (.querySelector js/document ".app"))

(defn render-app! [renderer] (renderer mount-target (comp-container @*reel) dispatch!))

(defn main! []
  (if ssr? (render-app! realize-ssr!))
  (render-app! render!)
  (add-watch *reel :changes (fn [] (render-app! render!)))
  (listen-devtools! "a" dispatch!)
  (.addEventListener
   js/window
   "click"
   (fn [event]
     (if (not (empty? (:popups (:store @*reel)))) (dispatch! :popup/clear-float nil))))
  (println "app started!"))

(defn reload! []
  (clear-cache!)
  (reset! *reel (refresh-reel @*reel schema/store updater))
  (println "code update."))

(set! (.-onload js/window) main!)
