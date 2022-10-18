package com.example.mywishes.base

import androidx.lifecycle.*
import com.example.mywishes.restApi.ApiResponse

abstract class BaseViewModel: ViewModel() {

    val errorLiveData = MutableLiveData<ApiResponse<*>>()

    var navigator: BaseNavigator? = null

    /**
     * [errorObserver] must be attached with [errorLiveData]
     */
    fun setErrorObserver(owner: LifecycleOwner, errorObserver: Observer<ApiResponse<*>>) {
        errorLiveData.observe(owner, errorObserver)
    }

    override fun onCleared() {
        navigator = null
        super.onCleared()
    }
}