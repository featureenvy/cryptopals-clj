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

(defn byte-array->binary-str
  [byte-array]
  (clojure.string/join (map byte->binary-str byte-array)))

(defn byte-array->base64-str
  [input]
  (->> input
       byte-array->binary-str
       partition-to-6-complements
       (map #(Integer/parseInt % 2))
       (map #(get mapping-table %))
       clojure.string/join))

(defn hex-str->byte-array
  [hex-str]
  (->> hex-str
       (partition 2 2 (repeat \0))
       (map clojure.string/join)
       (map #(Byte/parseByte % 16))))

(defn hex->base64
  [input]
  (-> input
      hex-str->byte-array
      byte-array->base64-str))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
