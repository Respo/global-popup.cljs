
(ns global-popup.comp.popup-stack
  (:require-macros [respo.macros :refer [defcomp div <> span]])
  (:require [respo.core :refer [create-comp]]
            [respo-ui.style :as ui]
            [hsl.core :refer [hsl]]))

(def style-box
  {:background-color (hsl 0 0 100),
   :width 0,
   :height 0,
   :transition-timing-function "ease-in-out",
   :transition-duration "400ms",
   :border-radius "2px"})

(defn on-backdrop [e dispatch!] (dispatch! :popup/drop nil))

(def style-theme
  (merge
   ui/center
   {:transition-duration "400ms",
    :transition-timing-function "ease-in-out",
    :transform "scale(0)",
    :position "fixed",
    :top 0,
    :left 0,
    :width 0,
    :height 0,
    :overflow "auto"}))

(defn render-modal [popup render-popup]
  (div
   {:style (merge
            style-theme
            {:background-color (hsl 0 0 0 0.3),
             :transform "scale(1)",
             :width (.-innerWidth js/window),
             :height (.-innerHeight js/window)}),
    :on {:click on-backdrop}}
   (div
    {:style (merge
             style-box
             {:width 480,
              :height 160,
              :background-color (hsl 0 0 100),
              :padding 16,
              :opacity 1}),
     :on {:click (fn [e dispatch!] )}}
    (render-popup popup))))

(defn render-popover [popup render-popup]
  (let [position (:position popup)]
    (div
     {:style (merge
              style-theme
              {:left (:x position),
               :top (:y position),
               :width (:w position),
               :height (:h position),
               :transform "scale(1)",
               :box-shadow (str "0 0 8px " (hsl 0 0 0 0.3))})}
     (div
      {:style (merge style-box {:width (:w position), :height (:h position)})}
      (render-popup popup)))))

(defcomp
 comp-popup-stack
 (popups inside-popup)
 (div
  {}
  (seq
   (conj
    (->> popups
         (map-indexed
          (fn [idx popup]
            [idx
             (case (:type popup)
               :popover (render-popover popup inside-popup)
               :modal (render-modal popup inside-popup)
               nil)]))
         (into []))
    [(count popups) (div {:style (merge style-theme)} (div {:style (merge style-box)}))]))))
