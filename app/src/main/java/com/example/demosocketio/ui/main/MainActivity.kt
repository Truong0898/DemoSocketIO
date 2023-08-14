package com.example.demosocketio.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.demosocketio.R
import com.example.demosocketio.databinding.ChatFragmentBinding
import com.example.demosocketio.databinding.LoginFragmentBinding
import com.example.demosocketio.ui.adapter.MessageAdapter
import com.example.demosocketio.ui.data.SocketHandler
import com.example.demosocketio.ui.fragment.ChatFragment

class MainActivity : ComponentActivity(), OnClickListener {
    private lateinit var binding: LoginFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnJoin -> {
                if (binding.edtName.text.isNotEmpty())
                    intent = Intent(this, ChatFragment::class.java)
                intent.putExtra(ChatFragment.USERNAME,true)
                startActivity(intent)
            }

        }

    }

}

