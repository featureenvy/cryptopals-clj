(ns cryptopals.core-test
  (:require [clojure.test :refer :all]
            [cryptopals.core :refer :all]))

(deftest hex-str->byte-array-test
  (testing "Converts a hex string into a byte array"
    (is (= [74] (vec (hex-str->byte-array "4A"))))))

(deftest hex-str->binary-str-test
  (testing "Converts a hex string into a binary string"
    (is (= "1001010" (hex-str->binary-str "4A")))))

(deftest byte-array->binary-str-test
  (testing "Converts byte array to binary string"
    (is (= "100101001010010" (byte-array->binary-str [74 82])))))

(deftest byte-array->base64-str-test
  (testing "Converts byte array to base64 string"
    (is (= "SSc=" (byte-array->base64-str [73 39])))))

(deftest str->base64-test
  (testing "Converts from str to base64"
    (is (= "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t" (hex->base64 "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d")))))

(deftest xor-test
  (testing "XORing two hex encoded strings"
    (is (= "746865206b696420646f6e277420706c6179" (xor "1c0111001f010100061a024b53535009181c" "686974207468652062756c6c277320657965")))))
