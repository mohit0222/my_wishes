package com.example.mywishes

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mywishes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnLogin.setOnClickListener {
            if (checkValidations()){
//                val intent = Intent(this, SignUpActivity::class.java)
//                startActivity(intent)
            }
        }
        binding.txtRegister.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkValidations():Boolean{
        val email = binding.edt1.text.toString()
        val password = binding.edt2.text.toString()
        if (!checkIfEmailIsValid(email)){
            Toast.makeText(this,"Please enter Email",Toast.LENGTH_SHORT).show()
            return false
        }else if (!isPasswordValid(password)){
            Toast.makeText(this,"Please enter Password",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun isPasswordValid(password: String): Boolean{
        if (password.length < 8) {
            return false
        }
        if (!password.matches(".*[A-Z].*".toRegex())){
            return false
        }
        if (!password.matches(".*[a-z].*".toRegex())) {
            return false
        }
        if (!password.matches(".*[@#\$%^&+=].*".toRegex())) {
            return false
        }
        return true
    }

    private fun checkIfEmailIsValid(email: String): Boolean {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true
        }
        return false
    }
}











