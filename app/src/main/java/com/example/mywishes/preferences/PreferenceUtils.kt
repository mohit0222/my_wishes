package com.example.mywishes.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.mywishes.utilities.BaseApplication

object PreferenceUtils {
    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor
    init {
        sharedPreferences = BaseApplication.getApplicationContext().getSharedPreferences("MY_WISHES_STORAGE", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }


    fun putString(key: String, value: String){
        editor.putString(key,value)
        editor.apply()
    }


    fun getString(key: String, defaultValue: String): String?{
        return sharedPreferences.getString(key,defaultValue)
    }

    fun putBoolean(key: String, value: Boolean){
        editor.putBoolean(key,value)
        editor.apply()
    }


    fun getBoolean(key: String, defaultValue: Boolean): Boolean{
        return sharedPreferences.getBoolean(key,defaultValue)
    }

    fun logout(){
        editor.clear()
        editor.apply()
    }
}