package com.example.mywishes.utilities

import android.app.Activity
import android.view.View
import android.widget.Toast

class ViewUtils {


}

fun View.setVisiblity(isVisible: Boolean){
    this.visibility = if (isVisible)View.VISIBLE else View.GONE
}

fun Activity.showToast(message: String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}