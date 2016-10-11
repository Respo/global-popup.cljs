
(ns global-popup.util)

(def old-id-ref (atom 0))

(defn gen-id! [] (swap! old-id-ref inc) @old-id-ref)
