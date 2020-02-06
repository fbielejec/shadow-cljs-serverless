(ns api.graphql
  (:require [cljs.nodejs :as nodejs]
            ["apollo-server-lambda" :refer (ApolloServer)]))

(nodejs/enable-util-print!)

(def schema "
schema {
    query:Query
}

type Query {
    hello(names: [String]): [Greeting]
}

type Greeting {
    id: ID!
    text: String
}
")

(def resolvers {:Query {:hello (fn [_ args _]
                                 (let [{:keys [:names]} (js->clj args :keywordize-keys true)]
                                   (clj->js (map (fn [n]
                                                   {:id n
                                                    :text (str "hello " n)})
                                                 names))))}})

(defn create-handler [event context callback]
  (let [handler (js-invoke (new ApolloServer (clj->js {:typeDefs schema
                                                       :resolvers resolvers})) "createHandler")]
    (handler event context callback)))
