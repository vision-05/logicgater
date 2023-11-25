(ns lgcore.core
  (:gen-class))


(defn nand [a b]
  (bit-and-not a b))

(def gates {:nand nand})

(defn new-gate [m k ls]
  (assoc m k [ls (fn [a] (a))]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
