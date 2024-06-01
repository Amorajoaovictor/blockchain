;; src/blockchain/util.clj
(ns blockchain.util)

(import 'java.security.MessageDigest)
(import 'javax.xml.bind.DatatypeConverter)

(defn digest [password algorithm encoding]
  (let [message-digest (MessageDigest/getInstance algorithm)
        bytes (.digest message-digest (.getBytes password encoding))]
    (DatatypeConverter/printHexBinary bytes)))
