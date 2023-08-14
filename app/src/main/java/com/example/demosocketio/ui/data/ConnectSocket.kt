package com.example.demosocketio.ui.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.google.gson.Gson
import java.net.URISyntaxException


class SocketHandler( ) {
    private  var _onNewChat = MutableLiveData<ModelMessage> ()
    val onNewChat: LiveData<ModelMessage> get() = _onNewChat
    private var socket: Socket? = null

    init {
        try {
            socket = IO.socket(SOCKET_URL)
            socket?.connect()

            registerOnNewChat()
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }

    private fun registerOnNewChat() {
        socket?.on(CHAT_KEY.NEW_MESSAGE) { args ->
            args.let { d->
                if (d.toString().isNotEmpty()) {
                    val modelMessage = Gson().fromJson(d.toString(),ModelMessage::class.java)
                    _onNewChat.postValue(modelMessage)
                }
            }

        }
    }


    fun emiChat(modelMessage: ModelMessage) {
        val jsonStr = Gson().toJson(modelMessage,ModelMessage::class.java)
        socket?.emit(CHAT_KEY.NEW_MESSAGE,jsonStr)
    }

    private object CHAT_KEY {
        const val NEW_MESSAGE = "new_message"
    }

    fun disconnectSocket(): Socket? {
        socket?.disconnect()
        socket?.off()
        return TODO()
    }




    companion object {
        const val SOCKET_URL = "wss://free.blr2.piesocket.com/v3/1?api_key=JCEeji6M0LFgXwNzsnFLvqaBLZ40NGcNKUAj30yc&notify_self=1"
    }
}


