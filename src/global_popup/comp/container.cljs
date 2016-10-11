
(ns global-popup.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.alias :refer [create-comp div span]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-text]]))

(def style-bar {:padding "8px 16px"})

(defn render []
  (fn [state mutate!]
    (div
      {:style (merge ui/global)}
      (div
        {:style style-bar}
        (div {:style ui/button} (comp-text "add modal" nil)))
      (div
        {:style style-bar}
        (div {:style ui/button} (comp-text "add popup" nil))))))

(def comp-container (create-comp :container render))
