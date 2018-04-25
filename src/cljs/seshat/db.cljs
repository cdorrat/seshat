(ns seshat.db)

(def default-db
  {:name "re-frame"
   :data {:services ["gatekeeper.transactions.foo"
                     "gatekeeper.transactions.count"
                     "gatekeeper.transactions.rate"
                     "other.component.stuff"
                     "other.somethings"]}})
