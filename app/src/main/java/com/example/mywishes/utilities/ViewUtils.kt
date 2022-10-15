package com.example.mywishes.utilities

import android.view.View

class ViewUtils {


}

fun View.setVisiblity(isVisible: Boolean){
    this.visibility = if (isVisible)View.VISIBLE else View.GONE
}