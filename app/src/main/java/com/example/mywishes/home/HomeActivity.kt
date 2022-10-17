package com.example.mywishes.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mywishes.R
import com.example.mywishes.databinding.ActivityHomeBinding
import com.example.mywishes.preferences.PreferenceUtils
import com.example.mywishes.utilities.gotoLogin

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        logout()


    }

    fun logout(){
        binding.btnLogout.setOnClickListener{
            PreferenceUtils.logout()
            gotoLogin(this)
        }


    }
}