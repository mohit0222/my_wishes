package com.example.mywishes.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mywishes.databinding.ActivityMainBinding
import com.example.mywishes.signUp.SignUpActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)



        observeLiveData()

        binding.txtRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.edt1.text.toString()
            val password = binding.edt2.text.toString()
            loginViewModel.checkValidations(email, password)
        }
    }

    fun observeLiveData() {

        loginViewModel.onErrorLiveData.observe(this, Observer {
            if (it == EMAIL_VALIDATION_FAILED) {
                Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show()
            } else if (it == PASSWORD_VALIDATION_FAILED) {
                Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show()
            }
        })

        loginViewModel.validationComplete.observe(this, Observer {
            if (it) {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
        })
    }

}











