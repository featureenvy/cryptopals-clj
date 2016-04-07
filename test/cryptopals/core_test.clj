(ns cryptopals.core-test
  (:require [clojure.test :refer :all]
            [cryptopals.core :refer :all]))

(deftest mapping-table-test
  (testing "Returns correct values for upper case characters"
    (is (= \A (get mapping-table 0)))
    (is (= \Z (get mapping-table 25))))
  (testing "Returns correct values for lower case characters"
    (is (= \a (get mapping-table 26)))
    (is (= \z (get mapping-table 51))))
  (testing "Returns correct value for digit characters"
    (is (= \0 (get mapping-table 52)))
    (is (= \9 (get mapping-table 61))))
  (testing "Returns correct value for special characters"
    (is (= \+ (get mapping-table 62)))
    (is (= \\ (get mapping-table 63)))))

(deftest str->base64-test
  (testing "Converts from str to base64"
    (is (= "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t" (hex->base64 "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d")))))

