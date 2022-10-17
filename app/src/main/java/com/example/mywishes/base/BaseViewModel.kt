package com.example.mywishes.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mywishes.restApi.ApiResponse

abstract class BaseViewModel<T>: ViewModel() {
    protected val _onErrorLiveData = MutableLiveData<ApiResponse<*>>()
    val onErrorLiveData : LiveData<ApiResponse<*>> = _onErrorLiveData


    fun handleApiResponse(response: ApiResponse<*>): Boolean{
        if (response.status) {
            return true
        } else {
            _onErrorLiveData.postValue(response)
            return false
        }
    }
}