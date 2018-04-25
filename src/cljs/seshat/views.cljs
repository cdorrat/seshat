(ns seshat.views
  (:require [re-frame.core :as re-frame]
            [reagent.core :as rc]
            [seshat.subs :as subs]
            [react-dnd :as r-dnd]
            [react-sortable-tree :as rst]            
            [react-treebeard :as rtb]
            [react-json-inspector :as rji]
            )) 

;; home

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div (str "Hello from " @name ". This is the Home Page.")
     [:div [:a {:href "#/about"} "go to About Page"]]]))


(defn json-view-tree []
  [:> rji {:data (clj->js {"Chicken" {"Egg" {"dinner" {}}}})}])

;; about
(defn sample-tree []
  (let [treeData (clj->js [{:title "Chicken" :children [{:title "Egg" }] }])]
;;    [:> rji {:data treeData }]
#_    [(rc/adapt-react-class (new (.-Default rst)))
     {:treeData treeData
      :onChange (fn [& args]
                  (println "cb:" args)
                  )}]
    
    [(rc/adapt-react-class (.-Treebeard rtb))
     {:data (clj->js {:name "root"})
      :onToggle (fn [& args] (println "toggle: " args))
      :decorators (.-decorators rtb)
      :animations (.-animations rtb)}]
    ;;    [:div "foo"]
#_    [(rc/adapt-react-class noDnDTree #_SortableTree)
     {:treeData treeData
      :onChange (fn [& args]
                  (println "cb:" args)
                  )}]
#_    (rc/create-element (.-Treebeard rtb)
                       (clj->js {:data {:name "root"}
                                 :onToggle (fn [& args] (println "toggle: " args))}))
;;    (rc/create-element "div" #js {}  "foo3")
#_(rc/create-element (.-default rst)
                   (clj->js {:treeData [{:title "Chicken" :children [{:title "Egg" }] }]
                             :onChange (fn [& args]
                                         (println "cb:" args)
                                 )}))
    
    )
  )

(defn about-panel []
  [:div "This is the About Page....."
   [json-view-tree]
   [:div [:a {:href "#/"} "go to Home Page"]
    ]])


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))
