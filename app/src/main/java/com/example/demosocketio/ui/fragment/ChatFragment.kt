package com.example.demosocketio.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demosocketio.R
import com.example.demosocketio.databinding.ChatFragmentBinding
import com.example.demosocketio.ui.adapter.MessageAdapter
import com.example.demosocketio.ui.data.ModelMessage
import com.example.demosocketio.ui.data.SocketHandler


class ChatFragment : Fragment(), OnClickListener{

    private lateinit var binding: ChatFragmentBinding
    private lateinit var socketHandler: SocketHandler
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var rcvChat: RecyclerView
    private lateinit var context : Context
    private var lsMessage = mutableListOf<ModelMessage>()
    private lateinit var btnSend : Button
    private var userName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rcvChat = binding.rcvChat
        val linearLayoutManager = LinearLayoutManager(context)
        rcvChat.layoutManager = linearLayoutManager
        rcvChat.adapter = messageAdapter

        btnSend = binding.btnSend
        btnSend.setOnClickListener(this)


        userName = arguments?.getString("user_name").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChatFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnSend -> {
                val message = binding.edtMessage.text.toString()
                    val name = userName
                    if (message.isNotEmpty()) {
                        val modelMessage = ModelMessage()
                        modelMessage.name = name
                        modelMessage.message = message
                        socketHandler.emiChat(modelMessage)
                        lsMessage.add(modelMessage)
                        messageAdapter.submidChat(lsMessage)
                        binding.rcvChat.scrollToPosition(lsMessage.size -1 )
                    }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        socketHandler.disconnectSocket()
    }

}



