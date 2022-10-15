package com.example.mywishes.signUp

import com.example.mywishes.login.LoginResponse
import com.example.mywishes.restApi.ApiResponse
import com.example.mywishes.restApi.ApiService

class SignupRepo {

    suspend fun doRegister(email: String, password: String): ApiResponse<Any> {
        val map = HashMap<String, String>()
        map.put("email", email)
        map.put("password", password)
        return ApiService.getApiHandler().register(map)
    }
}