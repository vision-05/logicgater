(ns lgcore.core-test
  (:require [clojure.test :refer :all]
            [lgcore.core :as core]
            [lgcore.core-test-defs :as defs]))

(deftest nand-internal
  (is (= (defs/get-arity core/nand) 2)))

(deftest create-gate
  (testing "creating new gate maps"
    (is (= (last (:not defs/with-not)) (fn [a] (core/nand a a))))
    (is (last (:not (core/new-gate core/gates :not '(:nand 'a)))) (fn [a] (core/nand a a)))
    (is (last (:not (core/new-gate core/gates :not '(:nand 'a 'a)))) (fn [a] (core/nand a a)))
    (is (last (:not (core/new-gate core/gates :not '(:nand 'a 'b)))) (fn [a b] (core/nand a b)))
    (is (last (:not (core/new-gate core/gates :not '(:nand 'a 'b 'c)))) "too many parameters")))

(deftest read-gates)

(deftest write-gates)

(deftest eval-truth-table)
