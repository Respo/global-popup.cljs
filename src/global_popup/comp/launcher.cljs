
(ns global-popup.comp.launcher
  (:require [respo.core :refer [defcomp <> div span]] [respo-ui.core :as ui]))

(defn on-modal-add [e dispatch!] (dispatch! :popup/add {:type :modal, :name :demo}))

(defn on-popover-add [e dispatch!]
  (let [event (:original-event e)]
    (.stopPropagation event)
    (dispatch!
     :popup/add
     {:type :popover,
      :name :demo,
      :position {:x (.-clientX event), :y (.-clientY event), :w 320, :h 160}})))

(def style-bar {:padding "8px 16px"})

(defcomp
 comp-launcher
 ()
 (div
  {}
  (div {:style style-bar} (div {:style ui/button, :on-click on-modal-add} (<> "add modal")))
  (div
   {:style style-bar}
   (div {:style ui/button, :on-click on-popover-add} (<> "add popup")))))
