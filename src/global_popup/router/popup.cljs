
(ns global-popup.router.popup
  (:require [respo.alias :refer [div]]
            [respo.comp.text :refer [comp-text]]
            [global-popup.comp.launcher :refer [comp-launcher]]))

(defn inside-modal [popup]
  (case
    (:name popup)
    :demo
    (div {} (comp-launcher))
    (comp-text (pr-str popup) nil)))

(defn inside-popover [popup]
  (case
    (:name popup)
    :demo
    (div {} (comp-launcher))
    (comp-text (pr-str popup) nil)))

(defn inside-popup [popup]
  (case
    (:type popup)
    :popover
    (inside-popover popup)
    :modal
    (inside-modal popup)
    (comp-text (pr-str popup) nil)))
