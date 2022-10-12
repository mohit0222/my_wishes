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

    fun CheckNameIsValid(name: String): Boolean {
        if (!name.isEmpty()) {
            return true
        }
        return false
    }

    fun checkNumberIsValid(number: String): Boolean {
        if (!number.isEmpty()) {
            return true
        }
        return false
    }

    fun checkCPasswordIsValid(confirmPass: String): Boolean {
        if (!confirmPass.isEmpty()) {
            return true
        }
        return false
    }
}