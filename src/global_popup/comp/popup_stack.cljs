
(ns global-popup.comp.popup-stack
  (:require [respo.alias :refer [create-comp div]]
            [respo-ui.style :as ui]
            [hsl.core :refer [hsl]]
            [global-popup.style :as style]))

(def style-zero-inside
 (merge
   style/transition
   {:background-color (hsl 0 0 100),
    :width 0,
    :opacity 0,
    :padding 0,
    :height 0}))

(defn on-backdrop [e dispatch!] (dispatch! :popup/drop nil))

(defn render-modal [popup render-popup]
  (div
    {:style
     (merge
       style/transition
       style/style-modal
       {:width (.-innerWidth js/window),
        :height (.-innerHeight js/window)}),
     :event {:click on-backdrop}}
    (div
      {:style (merge style/transition style/style-box),
       :event {:click (fn [e dispatch!])}}
      (render-popup popup))))

(defn render-popover [popup render-popup]
  (div
    {:style
     (merge
       style/transition
       style/style-popover
       {:top (:y (:position popup)), :left (:x (:position popup))})}
    (div {:style style-zero-inside})
    (render-popup popup)))

(def style-zero
 (merge
   ui/center
   {:transform "scale(0)",
    :top 0,
    :width 0,
    :opacity 0,
    :position "fixed",
    :height 0,
    :left 0}))

(defn render [popups render-popup]
  (fn [state mutate!]
    (div
      {}
      (conj
        (->>
          popups
          (map-indexed
            (fn [idx popup] [idx
                             (case
                               (:type popup)
                               :popover
                               (render-popover popup render-popup)
                               :modal
                               (render-modal popup render-popup)
                               nil)]))
          (into []))
        [(count popups)
         (div
           {:style (merge style/transition style-zero)}
           (div {:style style-zero-inside}))]))))

(def comp-popup-stack (create-comp :popup-stack render))
