
(ns global-popup.style
  (:require [hsl.core :refer [hsl]] [respo-ui.style :as ui]))

(def style-box
 {:background-color (hsl 0 0 100),
  :width 480,
  :opacity 1,
  :padding 16,
  :height 160})

(def style-popover
 (merge
   ui/center
   {:box-shadow (str "1px 1px 8px " (hsl 0 0 10 0.4)),
    :transform "scale(1)",
    :background-color (hsl 0 0 100 0.9),
    :width 400,
    :position "fixed",
    :border-radius "2px",
    :height 120}))

(def style-modal
 (merge
   ui/fullscreen
   ui/center
   {:transform "scale(1)",
    :background-color (hsl 0 0 0 0.3),
    :width (.-innerWidth js/window),
    :height (.-innerHeight js/window)}))

(def transition
 {:transition-timing-function "ease-in-out",
  :transition-duration "400ms"})
