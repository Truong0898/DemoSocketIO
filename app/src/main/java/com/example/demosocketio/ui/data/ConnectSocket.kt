package com.example.demosocketio.ui.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener


class ConnectWebSocket{
    private lateinit var webSocket: WebSocket
    private  var _onNewChat = MutableLiveData<ModelMessage> ()
    val onNewChat: LiveData<ModelMessage> get() = _onNewChat
    fun connectWebSocket() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("wss://socketsbay.com/wss/v2/1/demo/")
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                println("WebSocket connection opened")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                val modelMessage = ModelMessage()
                modelMessage.name = "Server"
                modelMessage.message = text
                modelMessage.isSend = false
                _onNewChat.postValue(modelMessage)
                println("Received message: $text")
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
                println("WebSocket connection closed")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                println("WebSocket connection failed: ${t.message}")
            }
        })
    }


    fun sendMessage(message: String) {
        webSocket.send(message)
    }

    fun closeWebSocket() {
        webSocket.close(1000, "Closing WebSocket connection")
    }

}

