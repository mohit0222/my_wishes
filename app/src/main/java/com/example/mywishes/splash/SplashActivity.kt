package com.example.mywishes.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mywishes.R
import com.example.mywishes.home.HomeActivity
import com.example.mywishes.login.MainActivity
import com.example.mywishes.preferences.IS_LOGGED_IN
import com.example.mywishes.preferences.PreferenceUtils
import com.example.mywishes.utilities.gotoHome
import com.example.mywishes.utilities.gotoLogin

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


    }

    fun findNextScreen(){
        val isLoggedIn = PreferenceUtils.getBoolean(IS_LOGGED_IN,false)
        if (isLoggedIn){
            // Go Home
            gotoHome(this)
        }else{
            // Go Login
            gotoLogin(this)
        }
    }


}