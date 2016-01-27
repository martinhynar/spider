(defproject
  spider "0.1.0"

  :description "Collection of functions for network related calculations."

  :url "https://github.com/martinhynar/spider"

  :scm {:name "git"
        :url  "https://github.com/martinhynar/spider"}

  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.8.0"]]

  :global-vars {*warn-on-reflection* true
                *assert* false}

  :repl-options {:init-ns spider.core
                 :port    40001})
