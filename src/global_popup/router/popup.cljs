
(ns global-popup.router.popup
  (:require [respo.alias :refer [div]]
            [respo.comp.text :refer [comp-text]]))

(defn render-modal [popup]
  (case
    (:name popup)
    :demo
    (div {} (comp-text "demo" nil))
    (comp-text (pr-str popup) nil)))

(defn render-popover [popup]
  (case
    (:name popup)
    :demo
    (div {} (comp-text "demo" nil))
    (comp-text (pr-str popup) nil)))

(defn render-popup [popup]
  (case
    (:type popup)
    :popover
    (render-popover popup)
    :modal
    (render-modal popup)
    (comp-text (pr-str popup) nil)))
