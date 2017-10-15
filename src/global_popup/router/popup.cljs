
(ns global-popup.router.popup
  (:require-macros [respo.macros :refer [defcomp <> div span]])
  (:require [respo.core :refer [create-comp]]
            [global-popup.comp.launcher :refer [comp-launcher]]))

(defn inside-modal [popup]
  (case (:name popup) :demo (div {} (comp-launcher)) (<> (pr-str popup))))

(defn inside-popover [popup]
  (case (:name popup) :demo (div {} (comp-launcher)) (<> (pr-str popup))))

(defn inside-popup [popup]
  (case (:type popup)
    :popover (inside-popover popup)
    :modal (inside-modal popup)
    (<> (pr-str popup))))
