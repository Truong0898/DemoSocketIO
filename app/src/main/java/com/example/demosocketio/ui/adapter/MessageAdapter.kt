package com.example.demosocketio.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demosocketio.databinding.ItemReceivedMessageBinding
import com.example.demosocketio.databinding.ItemSendMessageBinding
import com.example.demosocketio.ui.MyConst
import com.example.demosocketio.ui.data.ModelMessage

class MessageAdapter(var lsMess: List<ModelMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var bindingR: ItemReceivedMessageBinding
    private lateinit var bindingS: ItemSendMessageBinding
    private val differCallBack = object : DiffUtil.ItemCallback<ModelMessage>() {
        override fun areItemsTheSame(oldItem: ModelMessage, newItem: ModelMessage): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ModelMessage, newItem: ModelMessage): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallBack)

    fun submidChat(modelMessage: MutableList<ModelMessage>) {
        val lsMessage = differ.currentList
        lsMessage.add(modelMessage)
        differ.submitList(lsMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == MyConst.ITEM_SEND) {
            val bindingS = ItemSendMessageBinding.inflate(
                LayoutInflater
                    .from(parent.context), parent, false
            )
            return SendHolder(bindingS)
        } else {
            val bindingR = ItemReceivedMessageBinding.inflate(
                LayoutInflater
                    .from(parent.context), parent, false
            )
            return ReceivedHolder(bindingR)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val chat = differ.currentList[position]
        return if (chat.isSend) MyConst.ITEM_SEND else MyConst.ITEM_RECEIVED

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = differ.currentList[position]
        if (chat.isSend) {
            (holder as SendHolder).bind(chat)
        } else {
            (holder as ReceivedHolder).bind(chat)
        }
    }


}