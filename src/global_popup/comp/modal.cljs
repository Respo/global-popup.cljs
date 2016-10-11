
(ns global-popup.comp.modal
  (:require [respo.alias :refer [create-comp div]]
            [respo-ui.style :as ui]
            [hsl.core :refer [hsl]]))

(def style-box
 {:min-width 480,
  :min-height 160,
  :background-color (hsl 0 0 100),
  :padding 16})

(def style-modal
 (merge ui/fullscreen ui/center {:background-color (hsl 0 0 0 0.3)}))

(defn on-backdrop [e dispatch!] (dispatch! :popup/drop nil))

(defn render [popup render-popup]
  (fn [state mutate!]
    (div
      {:style style-modal, :event {:click on-backdrop}}
      (div
        {:style style-box, :event {:click (fn [e dispatch!])}}
        (render-popup popup)))))

(def comp-modal (create-comp :modal render))
