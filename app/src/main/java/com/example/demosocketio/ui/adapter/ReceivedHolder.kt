package com.example.demosocketio.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.demosocketio.databinding.ItemReceivedMessageBinding
import com.example.demosocketio.ui.data.ModelMessage

class ReceivedHolder(val bindingR: ItemReceivedMessageBinding): RecyclerView.ViewHolder(bindingR.root) {
    fun bind(mess: ModelMessage) {
        bindingR.modelMessage = mess
    }
}