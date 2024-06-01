(ns blockchain.core
  (:gen-class))

(import 'java.security.MessageDigest)
(import 'javax.xml.bind.DatatypeConverter)

(defn digest [password algorithm encoding]
  (let [message-digest (MessageDigest/getInstance algorithm)
        bytes (.digest message-digest (.getBytes password encoding))]
    (DatatypeConverter/printHexBinary bytes)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (digest "password" "SHA-256" "UTF-8")))
