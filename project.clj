(defproject
  spider "0.1.0-SNAPSHOT"

  :description "Collection of functions for network related calculations."

  :url "https://github.com/martinhynar/spider"

  :scm {:name "git"
        :url  "https://github.com/martinhynar/spider"}

  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.6.0"]]


  :repl-options {:init-ns spider.core
                 :port    40001})