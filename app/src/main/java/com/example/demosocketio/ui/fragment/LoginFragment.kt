package com.example.demosocketio.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.demosocketio.R
import com.example.demosocketio.databinding.LoginFragmentBinding


class LoginFragment : Fragment(), OnClickListener {
    private lateinit var btnJoin: Button
    private lateinit var binding: LoginFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = LoginFragmentBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnJoin = binding.btnJoin
        btnJoin.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnJoin -> {
                val bundle = Bundle()
                bundle.putString("user_name",binding.edtName.text.toString())
                if (binding.edtName.text.isNotEmpty()) {
                    val chatFragment = ChatFragment()
                    chatFragment.arguments = bundle
                    var chatfragment = fragmentManager?.beginTransaction()
                    chatfragment?.replace(R.id.flFragment, chatFragment)
                    chatfragment?.commit()
                }

            }
        }
    }
}