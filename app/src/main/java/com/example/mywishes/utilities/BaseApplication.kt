package com.example.mywishes.utilities

import android.app.Application
import android.content.Context

class BaseApplication: Application() {

    init {
        instance = this
    }
    companion object{
        var instance: BaseApplication ?= null
        fun getApplicationContext(): Context{
            return instance!!.getApplicationContext()
        }
    }
}