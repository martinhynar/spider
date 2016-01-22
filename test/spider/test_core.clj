(ns spider.test-core
  (:require [spider.core :as sc]
            [clojure.test :refer :all]))

(deftest about-unsigned-byte
  (testing "Range 0 to 127 remains unchanged"
    (is (= (sc/unsigned-byte 0) (byte 0)))
    (is (= (sc/unsigned-byte 127) (byte 127))))
  (testing "Range 128 to 255 becomes megative range -128 to -1"
    (is (= (sc/unsigned-byte 128) -128))
    (is (= (sc/unsigned-byte 255) -1)))
  (testing "Range <0 and >255 becomes nil"
    (is (nil? (sc/unsigned-byte -1)))
    (is (nil? (sc/unsigned-byte 256)))))

(deftest about-ip-to-long
  (testing "Valid IP addreses"
    (is (= (sc/ip-to-long "0.0.0.0") (long 0)))
    (is (= (sc/ip-to-long "0.0.0.") (long 0)))
    (is (= (sc/ip-to-long "0.0.0") (long 0)))
    (is (= (sc/ip-to-long "192.168.25.30") (long 3232241950)))
    (is (= (sc/ip-to-long "255.255.255.255") (long 4294967295))))
  (testing "Invalid IP addreses"
    (is (nil? (sc/ip-to-long "256.255.255.255")))
    (is (nil? (sc/ip-to-long "255.256.255.255")))
    (is (nil? (sc/ip-to-long "255.255.256.255")))
    (is (nil? (sc/ip-to-long "255.255.255.256")))
    (is (nil? (sc/ip-to-long ".0.0.0")))
    (is (nil? (sc/ip-to-long "-1.0.0.0")))
    (is (nil? (sc/ip-to-long "invalid")))))

(deftest about-long-to-ip
  (testing "Valid numbers"
    (is (= (sc/long-to-ip (long 0)) "0.0.0.0"))
    (is (= (sc/long-to-ip Long/MIN_VALUE) "0.0.0.0"))
    (is (= (sc/long-to-ip (long 3232241950)) "192.168.25.30"))
    (is (= (sc/long-to-ip (long 4294967295)) "255.255.255.255"))
    (is (= (sc/long-to-ip Long/MAX_VALUE) "255.255.255.255"))))

(deftest about-ip-in-range
  (testing "In range"
    (is (true? (sc/ip-in-range? "192.168.25.30" "0.0.0.0" "255.255.255.255")))
    ))
