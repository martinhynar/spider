(ns spider.core
  (:import (java.net InetAddress UnknownHostException)))


(def masks {:32 "255.255.255.255"
            :31 "255.255.255.254"
            :30 "255.255.255.252"
            :29 "255.255.255.248"
            :28 "255.255.255.240"
            :27 "255.255.255.224"
            :26 "255.255.255.192"
            :25 "255.255.255.128"
            :24 "255.255.255.0"
            :23 "255.255.254.0"
            :22 "255.255.252.0"
            :21 "255.255.248.0"
            :20 "255.255.240.0"
            :19 "255.255.224.0"
            :18 "255.255.192.0"
            :17 "255.255.128.0"
            :16 "255.255.0.0"
            :15 "255.254.0.0"
            :14 "255.252.0.0"
            :13 "255.248.0.0"
            :12 "255.240.0.0"
            :11 "255.224.0.0"
            :10 "255.192.0.0"
            :9  "255.128.0.0"
            :8  "255.0.0.0"})

(defn unsigned-byte
  "Takes value and turns it into unsigned byte value."
  [value]
  (cond
    (or (< value 0) (> value 255)) nil
    (>= value 128) (byte (- value 256))
    :default (byte value)))

(defn ip-to-long
  "Convert IP address to long representation. It is possible to pass either Java InetAddress or string.
   If string represents invalid IP, nil is returned."
  [ip]
  (cond
    (instance? String ip) (try
                            (let [ip (InetAddress/getByName ip)]
                              (ip-to-long ip))
                            (catch UnknownHostException _e nil))
    (instance? InetAddress ip) (let [octets (-> ip .getAddress)
                                     result 0]
                                 (loop [result result
                                        octets octets]
                                   (if (empty? octets)
                                     result
                                     ; loop part
                                     (let [result (bit-shift-left result 8)
                                           result (bit-or result (bit-and (first octets) 0xff))]
                                       (recur result (rest octets))))))))

(defn long-to-ip [ip-as-long]
  (let [octets (byte-array 4)]
    (aset-byte octets 0 (unsigned-byte (bit-and (unsigned-bit-shift-right ip-as-long 24) 0xff)))
    (aset-byte octets 1 (unsigned-byte (bit-and (unsigned-bit-shift-right ip-as-long 16) 0xff)))
    (aset-byte octets 2 (unsigned-byte (bit-and (unsigned-bit-shift-right ip-as-long 8) 0xff)))
    (aset-byte octets 3 (unsigned-byte (bit-and ip-as-long 0xff)))
    (-> (InetAddress/getByAddress (bytes octets)) .getHostAddress)))


(defn ip-in-range?
  "Check that ip-to-check lies in range delimited by rangeLo and rangeHi IP addresses"
  [ip-to-check rangeLo rangeHi]
  (let [ip? (ip-to-long (InetAddress/getByName ip-to-check))
        rangeLo (ip-to-long (InetAddress/getByName rangeLo))
        rangeHi (ip-to-long (InetAddress/getByName rangeHi))]
    (and (>= ip? rangeLo) (<= ip? rangeHi))))

(defn ip-in-subnet? [ip-to-check network mask]
  (let [mask (ip-to-long (or (masks mask) mask))]
    (= (bit-and (ip-to-long ip-to-check) mask) (bit-and (ip-to-long network) mask))))
