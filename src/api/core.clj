(ns api.core
  (:require
   [api.db :as db]
   [compojure.core :refer [defroutes GET]]
   [compojure.route :as route]
   [ring.adapter.jetty :as jetty]
   [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
   [ring.middleware.multipart-params :refer [wrap-multipart-params]]
   [ring.middleware.params :refer [wrap-params]])
   (:gen-class))

(defroutes app
  (GET  "/api/v1" [] {:status 200 :headers {"Content-Type" "application/json"} :body (db/select)} )
  (route/not-found "<h1>Page not found</h1>"))

(defn -main
  "Start the server"
  [& args]
  (jetty/run-jetty (-> app
                       (wrap-params :params)
                       (wrap-json-body {:keywords? true})
                       wrap-json-response
                       wrap-multipart-params)
                   {:port 3010}))
