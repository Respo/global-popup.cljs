
(ns global-popup.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.alias :refer [create-comp div span]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-text]]
            [global-popup.comp.popup-stack :refer [comp-popup-stack]]
            [respo-value.comp.value :refer [render-value]]
            [global-popup.router.popup :refer [render-popup]]))

(defn on-modal-add [e dispatch!]
  (dispatch! :popup/add {:name :demo, :type :modal}))

(defn on-popover-add [e dispatch!]
  (let [event (:original-event e)]
    (.stopPropagation event)
    (dispatch!
      :popup/add
      {:name :demo,
       :type :popover,
       :position {:y (.-clientY event), :x (.-clientX event)}})))

(def style-bar {:padding "8px 16px"})

(defn render [store]
  (fn [state mutate!]
    (div
      {:style (merge ui/global)}
      (div
        {:style style-bar}
        (div
          {:style ui/button, :event {:click on-modal-add}}
          (comp-text "add modal" nil)))
      (div
        {:style style-bar}
        (div
          {:style ui/button, :event {:click on-popover-add}}
          (comp-text "add popup" nil)))
      (comp-popup-stack (:popups store) render-popup)
      (render-value (last (:popups store))))))

(def comp-container (create-comp :container render))
