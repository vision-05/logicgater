(ns lgcore.core
  (:gen-class))

(def nand-internal {"00" 1
                    "10" 1
                    "01" 1
                    "11" 0})

(defn nand [a b]
  (nand-internal (str a b)))

(def gates {:nand nand
            :not (fn [m a] ((:nand m) a a))
            :and (fn [m a b] ((:not m) m ((:nand m) a b)))})

(defn eval-gate [m k & args]
  (apply (k m) m args))

(defn create-gate [m k ls]
  ())

(defn truth-table [m k] ())

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (eval-gate gates :not 0) (nand 1 0) (eval-gate gates :and 1 1)))
