(ns lgcore.core-test-defs
  (:require [lgcore.core :as core]))

(defn get-arity [f]
  (let [m (first (.getDeclaredMethods (class f)))
        p (.getParameterTypes m)]
    (alength p)))

(defn get-k-fn [kw m]
  (last (kw m)))

(def with-nand {:nand core/nand})

(def with-not [(core/create-gate with-nand :not '(fn [a] (:nand a a)))]) ;figure out what to test

