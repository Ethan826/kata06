(ns anagram.core-test
  (:require [clojure.test :refer :all]
            [anagram.core :refer :all]))

(deftest letter-counter-test
  (is (= 1 (letter-counter "a")))
  (is (= 2 (letter-counter "as")))
  (is (= 10 (letter-counter "Ethan Kent")))
  (is (= 0 (letter-counter ""))))

(def test-list #{"ham" "fork" "spoon" "knife" "jam" "dish"})
(def test-list-2 #{"tea" "eta" "ate" "meet"})
(def test-list-3 #{"tea" "eat" "ate" "tae" "moo" "cow" "pate"})

(deftest import-wordlist-test
  (is (= #{"kinship" "enlist" "boaster" "fresher" "sinks" "knits" "rots"}
         (import-wordlist (str (System/getProperty "user.dir") "/test/anagram/testwords.txt")))))

(deftest alphabetize-letters-test
  (is (= "act" (alphabetize-letters "cat")))
  (is (= "foo" (alphabetize-letters "ofo")))
  (is (= "" (alphabetize-letters ""))))

(deftest make-dictionary-test
  (is (= {"aet" #{"tea" "eat" "ate" "tae"}
          "moo" #{"moo"}
          "cow" #{"cow"}
          "aept" #{"pate"}}
         (make-dictionary test-list-3))))

(deftest make-anagram-list-test
  (is (= [["ate" "eat" "tae" "tea"]
          ["cow"]
          ["eat" "ate" "tae" "tea"]
          ["moo"]
          ["pate"]
          ["tae" "ate" "eat" "tea"]
          ["tea" "ate" "eat" "tae"]]
         (make-anagram-list test-list-3))))

