
(ns global-popup.comp.popover
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div]]
            [respo-ui.style :as ui]))

(def style-popover
 {:min-width 120,
  :box-shadow (str "1px 1px 8px " (hsl 0 0 10 0.4)),
  :min-height 40,
  :background-color (hsl 0 0 100 0.9),
  :position "fixed",
  :border-radius "2px"})

(defn render [popup render-popup]
  (fn [state mutate!]
    (div
      {:style
       (merge
         style-popover
         {:top (:y (:position popup)), :left (:x (:position popup))})}
      (render-popup popup))))

(def comp-popover (create-comp :popup render))
