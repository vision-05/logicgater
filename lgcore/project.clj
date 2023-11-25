(defproject lgcore "0.1.0-SNAPSHOT"
  :description "core of logicgater, with minimal CLI"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :main ^:skip-aot lgcore.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
