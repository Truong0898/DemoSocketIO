package com.example.demosocketio.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demosocketio.R
import com.example.demosocketio.databinding.ChatFragmentBinding
import com.example.demosocketio.ui.adapter.MessageAdapter
import com.example.demosocketio.ui.data.ConnectWebSocket
import com.example.demosocketio.ui.data.ModelMessage


class ChatFragment : Fragment(), OnClickListener {

    private lateinit var binding: ChatFragmentBinding
    private lateinit var connectWebSocket: ConnectWebSocket
    private lateinit var messageAdapter: MessageAdapter
    private var lsMessage = mutableListOf<ModelMessage>()
    private var userName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(context)
        binding.rcvChat.layoutManager = linearLayoutManager

        messageAdapter = MessageAdapter()
        binding.rcvChat.adapter = messageAdapter

        binding.btnSend.setOnClickListener(this)


        userName = arguments?.getString("user_name").toString()
        connectWebSocket = ConnectWebSocket()
        connectWebSocket.connectWebSocket()

        connectWebSocket.onNewChat.observe(viewLifecycleOwner, Observer {
            lsMessage.add(it)
            messageAdapter.submidChat(lsMessage)
            messageAdapter.notifyDataSetChanged()
            binding.rcvChat.scrollToPosition(lsMessage.size - 1)
        })
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
        when (v?.id) {
            R.id.btnSend -> {
                val message = binding.edtMessage.text.toString()
                val name = userName
                if (message.isNotEmpty()) {
                    val modelMessage = ModelMessage()
                    modelMessage.name = name
                    modelMessage.message = message
                    modelMessage.isSend = true
                    connectWebSocket.sendMessage(message)
                    lsMessage.add(modelMessage)
                    messageAdapter.submidChat(lsMessage)
                    messageAdapter.notifyDataSetChanged()
                    binding.rcvChat.scrollToPosition(lsMessage.size - 1)
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        connectWebSocket.closeWebSocket()
    }

}



