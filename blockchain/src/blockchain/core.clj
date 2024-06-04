(ns blockchain.core
  (:gen-class)
  (:require [blockchain.util :as util ])
  (:require [blockchain.env :as env ]))
(import 'java.security.MessageDigest)
(import 'javax.xml.bind.DatatypeConverter)

(defn -main
  [& args]
  (println "apikey:" env/apiKey))
(util/digest "password" "SHA-256" "UTF-8")
(def blockchain (atom {:id 0 :nonce 0 :hash "" :previousHash "" :body "inicio das transações"}))

(str (dissoc @blockchain :hash))

(defn mine-block [blockchain]
  (letfn [(mine [block]
            (let [hash (util/digest (str (dissoc block :hash)) "SHA-256" "UTF-8")]
              (if (= (subs hash 0 4) "0000")
                (do (swap! blockchain assoc :hash hash)
                    blockchain)
                (do (swap! blockchain update :nonce inc)
                    (recur @blockchain)))))]
    (mine @blockchain)))


(swap! blockchain assoc :hash (util/digest (str @blockchain) "SHA-256" "UTF-8"))
(mine-block blockchain)
blockchain



;; (defn minha-funcao
;;   ([] (minha-funcao "valor default")) ; Define a função sem argumentos, chamando a função com o valor default
;;   ([arg] 
;;     (println "O argumento é:" arg)
;;    arg)) ; Define a função com um argumento

;; ; Chamada sem argumento
;; (minha-funcao)
;; ; Chamada com argumento
;; (minha-funcao "meu valor")


