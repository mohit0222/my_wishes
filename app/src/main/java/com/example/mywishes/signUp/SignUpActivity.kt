package com.example.mywishes.signUp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mywishes.databinding.SignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: SignUpBinding
    private lateinit var signUpViewModel: signUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpBinding.inflate(layoutInflater)
        val View = binding.root
        setContentView(View)

        signUpViewModel = ViewModelProvider(this).get(signUpViewModel::class.java)



        observeLiveDataSign()
        binding.btnSignUp.setOnClickListener {
            val name = binding.edtEmail.text.toString()
            val number = binding.edtNumber.text.toString()
            val emailSign = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirmPass = binding.edtConfirmpassword.text.toString()

            signUpViewModel.checkValidations(name, number, emailSign, password, confirmPass)

        }

    }

    fun observeLiveDataSign() {

        signUpViewModel.onErrorLiveData.observe(this, Observer {
            if (it == Email_VALIDATION_FAILED) {
                Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show()
            } else if (it == password_VALIDATION_FAILED) {
                Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show()
            } else if (it == NAME_VALIDATION_FAILED) {
                Toast.makeText(this, "Please enter Name", Toast.LENGTH_SHORT).show()
            } else if (it == phone_VALIDATION_FAILED) {
                Toast.makeText(this, "Please enter Number", Toast.LENGTH_SHORT).show()
            } else if (it == confirm_password_VALIDATION_FAILED) {
                Toast.makeText(this, "Please enter confirm Password", Toast.LENGTH_SHORT).show()

            }
        })

        signUpViewModel.validationComplete.observe(this, Observer {
            if (it) {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
        })
    }

}