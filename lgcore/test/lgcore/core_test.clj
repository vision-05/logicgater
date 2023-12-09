(ns lgcore.core-test
  (:require [clojure.test :refer :all]
            [lgcore.core :as core]
            [lgcore.core-test-defs :as defs]))

(deftest nand-internal
  (is (= (defs/get-arity core/nand) 2)))

(deftest create-gate
  (testing "creating new gate maps"))

(deftest read-gates)

(deftest write-gates)

(deftest eval-truth-table)
