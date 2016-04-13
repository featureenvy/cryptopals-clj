(ns cryptopals.core
  (:gen-class))

(defn hex-str->byte-array
  [hex-str]
  (.toByteArray (BigInteger. hex-str 16)))

(defn byte-array->binary-str
  [bytes]
  (let [byte-array (into-array Byte/TYPE bytes)]
    (-> byte-array
        (BigInteger.)
        (.toString 2))))

(defn hex-str->binary-str
  [hex-str]
  (byte-array->binary-str (hex-str->byte-array hex-str)))

(defn byte-array->base64-str
  [bytes]
  (let [encoder (java.util.Base64/getEncoder)]
    (->> bytes
         (into-array Byte/TYPE)
         (.encodeToString encoder))))

(defn hex->base64
  [hex-str]
  (-> hex-str
      hex-str->byte-array
      byte-array->base64-str))

(defn xor
  [hex-input hex-key]
  (let [binary-input (hex-str->byte-array hex-input)
        binary-key (hex-str->byte-array hex-key)]
    (.toString (.xor (BigInteger. binary-input) (BigInteger. binary-key)) 16)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
