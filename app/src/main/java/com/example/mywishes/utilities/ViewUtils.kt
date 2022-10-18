package com.example.mywishes.utilities

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewUtils {


}

fun View.setVisiblity(isVisible: Boolean){
    this.visibility = if (isVisible)View.VISIBLE else View.GONE
}

fun Activity.showToast(message: String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(): T {
    return ViewModelProvider(this).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.getViewModel(): T {
    return ViewModelProvider(this).get(T::class.java)
}