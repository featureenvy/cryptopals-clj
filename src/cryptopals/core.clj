(ns cryptopals.core
  (:gen-class))

(defn generate-binary-triple->ascii-mapping
  [start-idx [start-char end-char]]
  (let [char-range (range (int start-char) (+ 1 (int end-char)))]
    (zipmap (iterate inc start-idx) (map char char-range))))

(defn generate-mapping-table []
  (merge (generate-binary-triple->ascii-mapping 0 [\A \Z])
         (generate-binary-triple->ascii-mapping 26 [\a \z])
         (generate-binary-triple->ascii-mapping 52 [\0 \9])
         {62 \+ 63 \\}))

(def ^:const mapping-table (generate-mapping-table))

(defn- byte->binary-str
  [val]
  (->> val
       Integer/toBinaryString
       Integer/parseInt
       (format "%08d")))

(defn- partition-to-6-complements
  [byte-array]
  (->> byte-array
       (partition 6 6 (repeat \0))
       (map clojure.string/join)))

(defn my-byte-array->binary-str
  [byte-array]
  (clojure.string/join (map byte->binary-str byte-array)))

(defn my-byte-array->base64-str
  [input]
  (->> input
       my-byte-array->binary-str
       partition-to-6-complements
       (map #(Integer/parseInt % 2))
       (map #(get mapping-table %))
       clojure.string/join))

(defn my-hex-str->byte-array
  [hex-str]
  (->> hex-str
       (partition 2 2 (repeat \0))
       (map clojure.string/join)
       (map #(Byte/parseByte % 16))))

(defn my-hex->base64
  [input]
  (-> input
      my-hex-str->byte-array
      my-byte-array->base64-str))

(defn hex-str->byte-array
  [hex-str]
  (vec (.toByteArray (BigInteger. hex-str 16))))

(defn byte-array->binary-str
  [bytes]
  (let [byte-array (into-array Byte/TYPE bytes)]
    (-> byte-array
        (BigInteger.)
        (.toString 2))))

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

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
