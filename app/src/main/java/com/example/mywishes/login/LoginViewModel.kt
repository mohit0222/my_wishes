package com.example.mywishes.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mywishes.base.BaseViewModel
import com.example.mywishes.restApi.ApiResponse
import com.example.mywishes.restApi.ResultWrapper
import com.example.mywishes.utilities.ValidationUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

const val EMAIL_VALIDATION_FAILED = 1
const val PASSWORD_VALIDATION_FAILED = 2

class LoginViewModel : BaseViewModel() {

    private val loginRepo = LoginRepo()
    private val _validationComplete = MutableLiveData<Boolean>()
    private val _onValidationErrorLiveData = MutableLiveData<Int>()
    private val _loginResponse = MutableLiveData<LoginResponse>()

    val validationComplete: LiveData<Boolean> = _validationComplete
    val onValidationErrorLiveData: LiveData<Int> = _onValidationErrorLiveData
    val loginResponse : LiveData<LoginResponse> = _loginResponse

    fun checkValidations(email: String, password: String) {
        if (!ValidationUtils.checkIfEmailIsValid(email)) {
            _onValidationErrorLiveData.value = EMAIL_VALIDATION_FAILED
        } else if (!ValidationUtils.isPasswordValid(password)) {
            _onValidationErrorLiveData.value = PASSWORD_VALIDATION_FAILED
        } else {
            _validationComplete.value = true
        }
    }

    fun dologin(email: String, password: String){
        GlobalScope.launch {
            val response = loginRepo.doLogin(email, password)
            if (response is ResultWrapper.Success) {
                _loginResponse.postValue(response.response?.data)
            }else if (response is ResultWrapper.GenericError){
                errorLiveData.postValue(response.response)
            }
        }
    }
}