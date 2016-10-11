
(ns global-popup.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.alias :refer [create-comp div span]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-text]]
            [global-popup.comp.popup-stack :refer [comp-popup-stack]]
            [respo-value.comp.value :refer [render-value]]
            [global-popup.router.popup :refer [render-popup]]
            [global-popup.comp.launcher :refer [comp-launcher]]))

(defn render [store]
  (fn [state mutate!]
    (div
      {:style (merge ui/global)}
      (comp-launcher)
      (comp-popup-stack (:popups store) render-popup)
      (render-value (last (:popups store))))))

(def comp-container (create-comp :container render))
