package com.example.demosocketio.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demosocketio.R
import com.example.demosocketio.ui.fragment.LoginFragment


class MainActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.loginFragment, LoginFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}

