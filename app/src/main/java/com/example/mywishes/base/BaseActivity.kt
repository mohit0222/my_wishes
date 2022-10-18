package com.example.mywishes.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.example.mywishes.R
import com.example.mywishes.databinding.ActivityHomeBinding
import com.example.mywishes.restApi.ApiResponse
import com.example.mywishes.utilities.showToast
import java.util.*
import kotlin.concurrent.thread

abstract class BaseActivity<MyViewBinding: ViewBinding,MyViewModel: BaseViewModel> : AppCompatActivity(), BaseNavigator {

    lateinit var binding: MyViewBinding
    var viewModel: MyViewModel? = null

    private var mProgressDialog: Dialog? = null

    abstract fun initViewModel(): MyViewModel?

    abstract fun initVariables()

    abstract fun setObservers()

    abstract fun inflateLayout(layoutInflater: LayoutInflater) : MyViewBinding



    private val errorObserver = Observer<ApiResponse<*>> {
        hideProgressBar()
        handleApiErrorResponse(it)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        setContentView(binding.root)

        initVariables()

        viewModel?.navigator = this
        viewModel?.setErrorObserver(this, errorObserver)

        setObservers()

    }


    private fun performDataBinding() {
        binding = inflateLayout(layoutInflater)
        viewModel = initViewModel()


    }




    /**
     * method to handle Api Error Response
     * Override this method to provide class level implementation
     */
    @CallSuper
    protected open fun handleApiErrorResponse(responseModel: ApiResponse<*>?) {
        if (responseModel?.status == false){
            showShortToast(responseModel.message)
        }
    }


    override fun showProgressBar() {
        if (!isFinishing) {
            hideProgressBar()
            mProgressDialog = Dialog(this)
            mProgressDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val view: View = LayoutInflater.from(this).inflate(R.layout.progress, null)
            mProgressDialog?.setContentView(view)

            mProgressDialog?.setCancelable(false)

            mProgressDialog?.window?.let {
                it.setBackgroundDrawable(
                    ContextCompat.getDrawable(this, android.R.color.transparent)
                )
                it.setDimAmount(0f)
                it.setGravity(Gravity.CENTER)
            }
            mProgressDialog?.show()
        }
    }


    override fun hideProgressBar() {
        if (!isFinishing && !isDestroyed) {
            if (mProgressDialog != null && mProgressDialog?.isShowing == true) {
                mProgressDialog?.dismiss()
            }
            mProgressDialog = null
        }
    }


    override fun showShortToast(msg: String?) {
        msg?.let {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }


    override fun showLongToast(msg: String?) {
        msg?.let {
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        }
    }




    override fun isNetworkAvailable(): Boolean {
        return true
    }

    override fun goBack() {

    }

    override fun showNoNetworkError() {

    }

    fun hideSoftKeyboard() {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val view = currentFocus
        if (view != null && (ev?.action == MotionEvent.ACTION_UP || ev?.action == MotionEvent.ACTION_MOVE)
            && view is EditText && !view.javaClass.name.startsWith("android.webkit.")
        ) {
            val scrcoords = IntArray(2)
            view.getLocationOnScreen(scrcoords)

            val x = ev.rawX + view.getLeft() - scrcoords[0]
            val y = ev.rawY + view.getTop() - scrcoords[1]

            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom()) {
                val inputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

                inputMethodManager.hideSoftInputFromWindow(
                    window.decorView.applicationWindowToken, 0
                )
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onDestroy() {
        hideProgressBar()
        super.onDestroy()
    }


}