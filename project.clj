(defproject anonydog "0.2.1"
  :description "Discord anonymous messaging bot"
  :url "N/A"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.suskalo/discljord "1.1.1"]]
  :repl-options {:init-ns anonydog.core}
  :main anonydog.core)
