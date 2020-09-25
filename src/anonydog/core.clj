(ns anonydog.core
  (:require [clojure.edn :as edn]
            [clojure.core.async :refer [chan close!]]
            [discljord.messaging :as discord-rest]
            [discljord.connections :as discord-ws]
            [discljord.formatting :refer [mention-user]]
            [discljord.events :refer [message-pump!]]))

(def state (atom nil))

(def bot-id (atom nil))

(def config (edn/read-string (slurp "config.edn")))

(defmulti handle-event (fn [type _data] type))

(defn random-response [user]
  (str (rand-nth (:responses config)) ", " (mention-user user) \!))

; (defn process-dm [])

(defmethod handle-event :message-create
  [_ {:keys [channel-id content author] :as mess-data}]
  ; (when (some #{@bot-id} (map :id mentions))
  ;   (discord-rest/create-message! (:rest @state) channel-id :content (random-response author))))
  ; (clojure.pprint/pprint @bot-id)
  ; (clojure.pprint/pprint (get author :id))
  (when
    (and
      (not (contains? mess-data :guild-id))
      (not= (get author :id) @bot-id))
    (discord-rest/create-message! (:rest @state) channel-id :content "Sending...")
    (discord-rest/create-message! (:rest @state) channel-id :content content)
    ))

(defmethod handle-event :ready
  [_ _]
  (discord-ws/status-update! (:gateway @state) :activity (discord-ws/create-activity :name (:playing config))))

(defmethod handle-event :default [_ _])

(defn start-bot! [token & intents]
  (let [event-channel (chan 100)
        gateway-connection (discord-ws/connect-bot! token event-channel :intents (set intents))
        rest-connection (discord-rest/start-connection! token)]
    {:events  event-channel
     :gateway gateway-connection
     :rest    rest-connection}))

(defn stop-bot! [{:keys [rest gateway events] :as _state}]
  (discord-rest/stop-connection! rest)
  (discord-ws/disconnect-bot! gateway)
  (close! events))

(defn -main [& args]
  (reset! state (start-bot! (:token config) :guild-messages :direct-messages))
  (reset! bot-id (:id @(discord-rest/get-current-user! (:rest @state))))
  (try
    (message-pump! (:events @state) handle-event)
    (finally (stop-bot! @state))))

