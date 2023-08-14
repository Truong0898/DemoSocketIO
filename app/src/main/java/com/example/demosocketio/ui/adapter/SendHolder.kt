package com.example.demosocketio.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.demosocketio.databinding.ItemSendMessageBinding
import com.example.demosocketio.ui.data.ModelMessage

class SendHolder(val bindingS: ItemSendMessageBinding): RecyclerView.ViewHolder(bindingS.root) {
    fun bind(modelMessage: ModelMessage) {
        bindingS.modelMessage = modelMessage
    }
}