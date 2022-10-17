package com.example.mywishes.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mywishes.restApi.ApiResponse
import com.example.mywishes.utilities.showToast

abstract class BaseActivity<T,V> : AppCompatActivity() {

    //protected var viewModel: BaseViewModel<T> = getMyViewModel()



    //abstract fun getMyViewModel(): BaseViewModel<T>


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        observeError()
    }

    fun observeError(){
        /*viewModel.onErrorLiveData.observe(this, Observer {
            onApiError(it)
        })*/
    }

    open fun onApiError(respone: ApiResponse<*>){
        showToast(respone.message)
    }


}