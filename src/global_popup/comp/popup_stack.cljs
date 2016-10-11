
(ns global-popup.comp.popup-stack
  (:require [respo.alias :refer [create-comp div]]
            [respo-ui.style :as ui]
            [hsl.core :refer [hsl]]
            [global-popup.comp.popover :refer [comp-popover]]
            [global-popup.comp.modal :refer [comp-modal]]))

(defn render [popups render-popup]
  (fn [state mutate!]
    (div
      {}
      (->>
        popups
        (map
          (fn [popup] [(:id popup)
                       (case
                         (:type popup)
                         :popover
                         (comp-popover popup render-popup)
                         :modal
                         (comp-modal popup render-popup)
                         nil)]))))))

(def comp-popup-stack (create-comp :popup-stack render))
