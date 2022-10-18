package com.example.mywishes.base

interface BaseNavigator {
    fun showProgressBar()
    fun hideProgressBar()
    fun showShortToast(msg: String?)
    fun showLongToast(msg: String?)
    fun isNetworkAvailable() : Boolean
    fun showNoNetworkError()
    fun goBack()
}