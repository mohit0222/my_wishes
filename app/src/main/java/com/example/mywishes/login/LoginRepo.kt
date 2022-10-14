package com.example.mywishes.login

import com.example.mywishes.restApi.ApiResponse
import com.example.mywishes.restApi.ApiService

class LoginRepo {

    suspend fun doLogin(email:String, password:String): ApiResponse<LoginResponse>{
        val map = HashMap<String,String>()
        map.put("email",email)
        map.put("password",password)
        return ApiService.getApiHandler().login(map)
    }
}