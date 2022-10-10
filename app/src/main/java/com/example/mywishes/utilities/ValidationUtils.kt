package com.example.mywishes.utilities

import android.util.Patterns

object ValidationUtils {

    fun isPasswordValid(password: String): Boolean {
        if (password.length < 8) {
            return false
        }
        if (!password.matches(".*[A-Z].*".toRegex())) {
            return false
        }
        if (!password.matches(".*[a-z].*".toRegex())) {
            return false
        }
        if (!password.matches(".*[@#\$%^&+=].*".toRegex())) {
            return false
        }
        return true
    }

    fun checkIfEmailIsValid(email: String): Boolean {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true
        }
        return false
    }
}