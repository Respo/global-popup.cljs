
(ns global-popup.updater.popup
  (:require [global-popup.schema :as schema]))

(defn clear-floating [popups]
  (filterv (fn [popup] (= (:type popup) :modal)) popups))

(defn clear-float [store op-data op-id]
  (update store :popups clear-floating))

(defn add-one [store op-data op-id]
  (update
    store
    :popups
    (fn [popups]
      (let [stable-popups (clear-floating popups)]
        (conj
          stable-popups
          (-> schema/popup (merge op-data) (assoc :id op-id)))))))

(defn drop-one [store op-data op-id]
  (update
    store
    :popups
    (fn [popups]
      (if (empty? popups)
        popups
        (let [len (count popups)] (subvec popups 0 (dec len)))))))
