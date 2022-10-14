package com.example.mywishes.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mywishes.utilities.ValidationUtils

const val NAME_VALIDATION_FAILED = 1
const val Email_VALIDATION_FAILED = 2
const val phone_VALIDATION_FAILED = 3
const val password_VALIDATION_FAILED = 4
const val confirm_password_VALIDATION_FAILED = 5


class SignUpViewModel : ViewModel() {

    private val _validationCompleteSign = MutableLiveData<Boolean>()
    private val _onErrorLiveDataSign = MutableLiveData<Int>()

    val validationComplete: LiveData<Boolean> = _validationCompleteSign
    val onErrorLiveData: MutableLiveData<Int> = _onErrorLiveDataSign

    fun checkValidations(
        name: String, email: String, number: String, password: String, confirmPass: String
    ) {
        if (!ValidationUtils.CheckNameIsValid(name)) {
            _onErrorLiveDataSign.value = NAME_VALIDATION_FAILED
        } else if (!ValidationUtils.checkIfEmailIsValid(email)) {
            _onErrorLiveDataSign.value = Email_VALIDATION_FAILED
        } else if (!ValidationUtils.checkNumberIsValid(number)) {
            _onErrorLiveDataSign.value = phone_VALIDATION_FAILED
        } else if (!ValidationUtils.isPasswordValid(password)) {
            _onErrorLiveDataSign.value = password_VALIDATION_FAILED
        } else if (!ValidationUtils.isPasswordValid(confirmPass)) {
            _onErrorLiveDataSign.value = confirm_password_VALIDATION_FAILED

        } else {
            _validationCompleteSign.value = true
        }
    }



}