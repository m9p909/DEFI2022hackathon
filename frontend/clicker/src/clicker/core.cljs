(ns clicker.core
  [:require [oops.core :refer [oset!]]
   [clojure.core.async :refer [<! timeout go-loop]]])

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(def canvas (.getElementById js/document "canvas"))
(def cookie_image (new js/Image))
(oset! cookie_image "src" "/images/cookie.jpg")

(defn printpass [prev]
  (print prev)
  prev)

(defn clear-canvas [ctx state]
  (.clearRect ctx 0 0 (.-width canvas) (.-height canvas))
  )

(defn render-cookie [ctx state]
  
  (.drawImage ctx cookie_image 0 0 150 150)

  state)

(defn cookie-clicker [ctx]
  (defn main-loop [state]
    (render-cookie ctx (clear-canvas ctx state))
    )
  
  (go-loop [state (main-loop {})]
    (<! (timeout 18))
    (recur (main-loop state))))

(defn start []
    (let [ctx (.getContext canvas "2d")]
      (cookie-clicker ctx)
      
      )
  )


(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (start))

(defn stop []
  ;; stop is called before any code is reloaded
  ;; this is controlled by :before-load in the config
  (js/console.log "stop"))
