
(ns global-popup.comp.launcher
  (:require [respo.alias :refer [create-comp div]]
            [respo-ui.style :as ui]
            [respo.comp.text :refer [comp-text]]))

(defn on-modal-add [e dispatch!] (dispatch! :popup/add {:name :demo, :type :modal}))

(defn on-popover-add [e dispatch!]
  (let [event (:original-event e)]
    (.stopPropagation event)
    (dispatch!
     :popup/add
     {:name :demo,
      :type :popover,
      :position {:y (.-clientY event), :w 320, :h 160, :x (.-clientX event)}})))

(def style-bar {:padding "8px 16px"})

(defn render []
  (fn [state mutate!]
    (div
     {}
     (div
      {:style style-bar}
      (div {:style ui/button, :event {:click on-modal-add}} (comp-text "add modal" nil)))
     (div
      {:style style-bar}
      (div {:style ui/button, :event {:click on-popover-add}} (comp-text "add popup" nil))))))

(def comp-launcher (create-comp :launcher render))
