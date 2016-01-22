# spider
Collection of functions for network related calculations.

#### Continuous integration
[![Build Status](https://travis-ci.org/martinhynar/spider.svg?branch=master)](https://travis-ci.org/martinhynar/spider)
[![Dependencies Status](https://jarkeeper.com/martinhynar/spider/status.svg)](https://jarkeeper.com/martinhynar/spider)

#### Latest version
[![Clojars Project](http://clojars.org/spider/latest-version.svg)](http://clojars.org/spider)

#### Usage guide

__Checking that address is within given range__

```
(spider.core/ip-in-range? "192.168.25.30" "192.168.25.0" "192.168.25.255")
;; true
```

__Checking that address is within given subnet__

```
(spider.core/ip-in-subnet? "192.168.25.0" "192.168.25.0" :24)
;; true
```

or equivalent call with explicit mask 

```
(spider.core/ip-in-subnet? "192.168.25.0" "192.168.25.0" "255.255.255.0")
;; true
```

## License
Distributed under the Eclipse Public License, the same as Clojure. Please see the [LICENSE](LICENSE) file.
