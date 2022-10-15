package com.example.mywishes.utilities

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.mywishes.home.HomeActivity
import com.example.mywishes.login.MainActivity


fun gotoLogin(context: Activity){
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
    context.finish()
}

fun gotoHome(context: Activity){
    val intent = Intent(context, HomeActivity::class.java)
    context.startActivity(intent)
    context.finish()
}