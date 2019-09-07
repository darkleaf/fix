(ns darleaf.fix-test
  (:require
   [darkleaf.fix :as fix]
   [clojure.test :as t]))

(t/deftest constructor
  (t/is (= #fix/re "\\d"
           #fix/re #"\d"
           #fix/regex "\\d"
           #fix/regex #"\d"
           (fix/->regex "\\d")
           (fix/->regex #"\d"))))

(t/deftest eq-test
  (t/is (= #fix/re "."
           #fix/re "."))
  (t/is (= (hash #fix/re ".")
           (hash #fix/re "."))))

(t/deftest match-test
  (let [re #fix/re "."
        s  "1"]
    (t/is (= (re-matches @re s)
             (re s)
             (apply re [s])))))

(t/deftest str-test
  (t/is (= (str #fix/re "\\d")
           "\\d"))
  (t/is (= (pr-str #fix/re "\\d")
           (pr-str #"\d"))))
