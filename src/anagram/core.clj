(ns anagram.core
  (:gen-class))

(defn import-wordlist [path]
  (set (clojure.string/split-lines (slurp path))))

(defn alphabetize-letters [word]
  (apply str (sort word)))

(defn make-dictionary [coll]
  (reduce
   (fn [accum el]
     (update accum (alphabetize-letters el) #(apply sorted-set (conj % el))))
   {}
   coll))

(defn make-anagram-list [coll]
  (let [entries (sort coll)
        dictionary (make-dictionary coll)]
    (reduce
     (fn [accum el]
       (conj
        accum
        (distinct (vec (cons el (get dictionary (alphabetize-letters el)))))))
     []
     entries)))

(defn -main [& args]
  (let [in-file (first args)
        out-file (second args)
        word-list (import-wordlist in-file)
        anagram-list (make-anagram-list word-list)
        out-string (clojure.string/join "\n" (map #(clojure.string/join " " %) anagram-list))]
    (spit out-file out-string)))
