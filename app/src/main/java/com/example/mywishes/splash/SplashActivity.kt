package com.example.mywishes.splash

import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.mywishes.R
import com.example.mywishes.preferences.IS_LOGGED_IN
import com.example.mywishes.preferences.PreferenceUtils
import com.example.mywishes.utilities.gotoHome
import com.example.mywishes.utilities.gotoLogin

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            findNextScreen()
        }, 3000)


    }

    fun findNextScreen() {
        val isLoggedIn = PreferenceUtils.getBoolean(IS_LOGGED_IN, false)
        if (isLoggedIn) {
            // Go Home
            gotoHome(this)
        } else {
            // Go Login
            gotoHome(this)
            //gotoLogin(this)
        }
    }


}