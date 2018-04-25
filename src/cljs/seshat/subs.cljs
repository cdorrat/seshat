(ns seshat.subs
  (:require [re-frame.core :as re-frame]
            [clojure.string :as str]
            [clojure.walk :as w]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::active-panel
 (fn [db _]
   (:active-panel db)))


(defn list-to-js-tree [names]
  (->> names 
       (reduce #(assoc-in %1 (str/split %2 #"\.") {}) {})
       (w/postwalk #(if (map? %)
                     (into []
                           (for [[k v] %] (merge {:label k}
                                                 (when-not (empty? v)
                                                   {:children v})))) 
                     %))))
