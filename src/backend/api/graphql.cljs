(ns api.graphql
  (:require [cljs.nodejs :as nodejs]
            ["apollo-server-lambda" :refer (ApolloServer)]))

(nodejs/enable-util-print!)

(def schema "
schema {
    query:Query
}

type Query {
    getTodos: [Todo]
}

type Todo {
    id: ID!
    name: String
    description: String
    priority: Int
}
")

(def resolvers {:Query {:getTodos (fn []
                                    (clj->js [{:id "0"
                                               :name "FOO"}
                                              {:id "1"
                                               :name "BAR"}]))}})

(defn create-handler [event context callback]
  (let [handler (js-invoke (new ApolloServer (clj->js {:typeDefs schema
                                                       :resolvers resolvers})) "createHandler")]
    (handler event context callback)))
