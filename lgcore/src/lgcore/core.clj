(ns lgcore.core
  (:gen-class))


(defn nand [a b]
  (bit-and-not a b))

(def gates {:nand nand})

;;ERRORS: keyword doesn't exist
(defn replace-kw-single [m ls] ;for a single level list
  (conj (rest ls) ((first ls) m)))

;function to go through list and replace all kws
(defn replace-all-kw [m ls]
  ())

(defn create-gate [m k ls]
  (assoc m k [ls (eval (replace-all-kw m ls))]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
