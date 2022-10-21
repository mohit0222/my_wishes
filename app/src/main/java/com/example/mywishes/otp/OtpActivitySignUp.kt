package com.example.mywishes.otp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mywishes.R
import com.example.mywishes.databinding.ActivityMainBinding
import com.example.mywishes.databinding.ActivityOtpBinding

class otpActivitySignUp : AppCompatActivity() {

    private lateinit var binding: ActivityOtpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityOtpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}