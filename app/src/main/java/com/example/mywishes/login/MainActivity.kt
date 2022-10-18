package com.example.mywishes.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.mywishes.base.BaseActivity
import com.example.mywishes.base.BaseViewModel
import com.example.mywishes.databinding.ActivityMainBinding
import com.example.mywishes.preferences.EMAIL
import com.example.mywishes.preferences.IS_LOGGED_IN
import com.example.mywishes.preferences.PreferenceUtils
import com.example.mywishes.restApi.ApiResponse
import com.example.mywishes.signUp.SignUpActivity
import com.example.mywishes.signUp.SignUpRequest
import com.example.mywishes.signUp.SignUpViewModel
import com.example.mywishes.utilities.getViewModel
import com.example.mywishes.utilities.gotoHome
import com.example.mywishes.utilities.setVisiblity


class MainActivity : BaseActivity<ActivityMainBinding,LoginViewModel,>() {

    override fun initViewModel() = getViewModel<LoginViewModel>()

    override fun initVariables() {

        binding.txtRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edt1.text.toString()
            val password = binding.edt2.text.toString()
            viewModel?.checkValidations(email, password)
        }
    }

    override fun setObservers() {
        viewModel?.onValidationErrorLiveData?.observe(this, Observer {
            if (it == EMAIL_VALIDATION_FAILED) {
                Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show()
            } else if (it == PASSWORD_VALIDATION_FAILED) {
                Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel?.validationComplete?.observe(this, Observer {

            if (it) {
                showProgressBar()
                val email = binding.edt1.text.toString()
                val password = binding.edt2.text.toString()
                viewModel?.dologin(email, password)
            }
        })

        viewModel?.loginResponse?.observe(this, Observer { response ->
            hideProgressBar()
            Toast.makeText(this, response.name, Toast.LENGTH_LONG).show()
            PreferenceUtils.putString(EMAIL,response.email)
            PreferenceUtils.putBoolean(IS_LOGGED_IN,true)
            gotoHome(this)
        })
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) = ActivityMainBinding.inflate(layoutInflater)


}











