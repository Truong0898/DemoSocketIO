package com.example.demosocketio.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demosocketio.R
import com.example.demosocketio.databinding.ChatFragmentBinding
import com.example.demosocketio.databinding.LoginFragmentBinding
import com.example.demosocketio.ui.adapter.MessageAdapter
import com.example.demosocketio.ui.data.ModelMessage
import com.example.demosocketio.ui.data.SocketHandler
import com.example.demosocketio.ui.fragment.ChatFragment

class MainActivity : ComponentActivity(), OnClickListener {
    private lateinit var socketHandler: SocketHandler
    private lateinit var binding: LoginFragmentBinding
    private lateinit var bindingC: ChatFragmentBinding
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var rcvChat: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        socketHandler = SocketHandler()
        rcvChat = bindingC.rcvChat
        val linearLayoutManager = LinearLayoutManager(this)
        rcvChat.layoutManager = linearLayoutManager
        rcvChat.adapter = messageAdapter

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSend -> {
                val message = bindingC.edtMessage.text.toString()
                if (message.isNotEmpty()) {
                    val name = binding.edtName.text.toString()
                    val message = bindingC.edtMessage.text.toString()
                    if (message.isNotEmpty()) {
                        val modelMessage = ModelMessage()
                        modelMessage.name = name
                        modelMessage.message = message
                        socketHandler.emiChat(modelMessage)
                    }
                }
            }

            R.id.btnJoin -> {
                if (binding.edtName.text.isNotEmpty())
                    intent = Intent(this, ChatFragment::class.java)
                startActivity(intent)
            }

        }

    }
}

