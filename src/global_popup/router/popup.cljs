
(ns global-popup.router.popup
  (:require [respo.core :refer [defcomp <> div span create-comp]]
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
