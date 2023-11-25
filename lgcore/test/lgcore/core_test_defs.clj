(ns lgcore.core-test-defs
  (:require [lgcore.core :as core]))

(defn get-arity [f]
  (let [m (first (.getDeclaredMethods (class f)))
        p (.getParameterTypes m)]
    (alength p)))

(defn get-k-fn [kw m]
  (last (kw m)))

(def with-not {:nand core/nand
               :not ['(:nand 'a) (fn [a] (core/nand a a))]})

(def with-and (assoc with-not :and ['(:not '(:nand 'a 'b)) (fn [a b] ((get-k-fn :not with-not) (core/nand a b)))])) ;consider using let when certain bits get reused 


