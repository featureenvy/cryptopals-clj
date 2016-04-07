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

(defn- hex-char-to-binary
  [hex]
  (let [digit (Character/digit hex 16)]
    (->> digit 
         Integer/toBinaryString
         Integer/parseInt
         (format "%04d"))))

(defn- hex-to-binary-string
  [hex-str]
  (clojure.string/join (map hex-char-to-binary hex-str)))

(defn- partition-to-6-complements
  [binary-str]
  (->> binary-str
       (partition 6 6 (repeat \0))
       (map clojure.string/join)))

(defn- map-to-base64-index
  [input]
  (->> input
       hex-to-binary-string
       partition-to-6-complements
       (map #(Integer/parseInt % 2))))

(defn hex->base64
  [input]
  (let [numbers (map-to-base64-index input)]
    (clojure.string/join (map #(get mapping-table %) numbers))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
