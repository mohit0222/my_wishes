package com.example.mywishes.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mywishes.utilities.ValidationUtils

const val EMAIL_VALIDATION_FAILED = 1
const val PASSWORD_VALIDATION_FAILED = 2

class LoginViewModel : ViewModel() {

    private val _validationComplete = MutableLiveData<Boolean>()
    private val _onErrorLiveData = MutableLiveData<Int>()

    val validationComplete: LiveData<Boolean> = _validationComplete
    val onErrorLiveData: LiveData<Int> = _onErrorLiveData

    fun checkValidations(email: String, password: String) {
        if (!ValidationUtils.checkIfEmailIsValid(email)) {
            _onErrorLiveData.value = EMAIL_VALIDATION_FAILED
        } else if (!ValidationUtils.isPasswordValid(password)) {
            _onErrorLiveData.value = PASSWORD_VALIDATION_FAILED
        } else {
            _validationComplete.value = true
        }
    }
}