
(ns global-popup.comp.container
  (:require-macros [respo.macros :refer [defcomp <> cursor-> div span]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [global-popup.comp.popup-stack :refer [comp-popup-stack]]
            [respo-value.comp.value :refer [comp-value]]
            [global-popup.router.popup :refer [inside-popup]]
            [global-popup.comp.launcher :refer [comp-launcher]]
            [reel.comp.reel :refer [comp-reel]]))

(defcomp
 comp-container
 (reel)
 (let [store (:store reel), states (:states store)]
   (div
    {:style (merge ui/global)}
    (comp-launcher)
    (comp-popup-stack (:popups store) inside-popup)
    (comp-value states (last (:popups store)))
    (<> reel)
    (comp-reel reel {}))))
