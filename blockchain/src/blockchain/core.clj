(ns blockchain.core
  (:gen-class)
  (:require [blockchain.util :as util]))
(import 'java.security.MessageDigest)
(import 'javax.xml.bind.DatatypeConverter)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "o codigo esta rodando confia no pai que o inimigo cai , sou mto foda papai"))
(util/digest "password" "SHA-256" "UTF-8")

(def blockchain (atom {:id 0 :nonce 0 :hash "" :previousHash "" :body "inicio das transações"}))
(str @blockchain)(defn mine-block [blockchain]
  (letfn [(mine [blockchain]
            (let [hash (util/digest (str @blockchain) "SHA-256" "UTF-8")]
              (if (= (subs hash 0 4) "0000")
                (do (swap! blockchain assoc :hash hash)
                    blockchain)
                (do (swap! blockchain update :nonce inc)
                    (recur blockchain)))))]
    (mine blockchain)))

(swap! blockchain assoc :hash (util/digest (str @blockchain) "SHA-256" "UTF-8"))
(mine-block blockchain)
blockchain

;; (swap! blockchain assoc :previousHash @blockchain)


;; (defn minha-funcao
;;   ([] (minha-funcao "valor default")) ; Define a função sem argumentos, chamando a função com o valor default
;;   ([arg] 
;;     (println "O argumento é:" arg)
;;    arg)) ; Define a função com um argumento

;; ; Chamada sem argumento
;; (minha-funcao)
;; ; Chamada com argumento
;; (minha-funcao "meu valor")


