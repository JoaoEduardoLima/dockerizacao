(ns api.db
  (:require [qbits.alia :as alia]))

(def cluster (alia/cluster {:session-keyspace "app"
                            :contact-points ["host.docker.internal"]
                            :port 9042
                            :load-balancing-local-datacenter "Analytics"}))

(def session (alia/connect cluster))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; STATEMENTS ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def prepared-statement-select
  (alia/prepare session "select * from app.linguagens_programacao;"))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; FUNCTIONS ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn select []
  (alia/execute session prepared-statement-select))