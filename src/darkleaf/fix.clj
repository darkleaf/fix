(ns darkleaf.fix
  (:import
   [java.util.regex Pattern]
   [clojure.lang IDeref IFn AFn]))

(deftype Regex [regex str]
  Object
  (toString [_] str)
  (hashCode [_]
    (.hashCode str))
  (equals [_ other]
    (and (instance? Regex other)
         (.equals str (.-str other))))

  IDeref
  (deref [_] regex)

  IFn
  (invoke [_ s]
    (re-matches regex s))
  (applyTo [this arglist]
    (AFn/applyToHelper this arglist)));

(defprotocol ToRegex
  (->regex [x]))

(extend-protocol ToRegex
  Pattern
  (->regex [x]
    (Regex. x (str x)))

  String
  (->regex [x]
    (Regex. (re-pattern x) x)))

(defmethod print-method Regex [v ^java.io.Writer w]
  (print-method @v w))
