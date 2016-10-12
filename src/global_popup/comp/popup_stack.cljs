
(ns global-popup.comp.popup-stack
  (:require [respo.alias :refer [create-comp div]]
            [respo-ui.style :as ui]
            [hsl.core :refer [hsl]]))

(def style-theme
 (merge
   ui/center
   {:transform "scale(0)",
    :top 0,
    :overflow "auto",
    :width 0,
    :transition-timing-function "ease-in-out",
    :position "fixed",
    :transition-duration "400ms",
    :height 0,
    :left 0}))

(def style-box
 {:background-color (hsl 0 0 100),
  :width 0,
  :transition-timing-function "ease-in-out",
  :border-radius "2px",
  :transition-duration "400ms",
  :height 0})

(defn on-backdrop [e dispatch!] (dispatch! :popup/drop nil))

(defn render-modal [popup render-popup]
  (div
    {:style
     (merge
       style-theme
       {:transform "scale(1)",
        :background-color (hsl 0 0 0 0.3),
        :width (.-innerWidth js/window),
        :height (.-innerHeight js/window)}),
     :event {:click on-backdrop}}
    (div
      {:style
       (merge
         style-box
         {:background-color (hsl 0 0 100),
          :width 480,
          :opacity 1,
          :padding 16,
          :height 160}),
       :event {:click (fn [e dispatch!])}}
      (render-popup popup))))

(defn render-popover [popup render-popup]
  (let [position (:position popup)]
    (div
      {:style
       (merge
         style-theme
         {:box-shadow (str "0 0 8px " (hsl 0 0 0 0.3)),
          :transform "scale(1)",
          :top (:y position),
          :width (:w position),
          :height (:h position),
          :left (:x position)})}
      (div
        {:style
         (merge
           style-box
           {:width (:w position), :height (:h position)})}
        (render-popup popup)))))

(defn render [popups inside-popup]
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
                               (render-popover popup inside-popup)
                               :modal
                               (render-modal popup inside-popup)
                               nil)]))
          (into []))
        [(count popups)
         (div
           {:style (merge style-theme)}
           (div {:style (merge style-box)}))]))))

(def comp-popup-stack (create-comp :popup-stack render))
