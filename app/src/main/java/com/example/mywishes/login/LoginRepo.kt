package com.example.mywishes.login

import com.example.mywishes.base.BaseRepository
import com.example.mywishes.restApi.ApiResponse
import com.example.mywishes.restApi.ApiService
import com.example.mywishes.restApi.ResultWrapper

class LoginRepo : BaseRepository(){

    suspend fun doLogin(email: String, password: String): ResultWrapper<ApiResponse<LoginResponse>?> {
        val map = HashMap<String, String>()
        map.put("email", email)
        map.put("password", password)
        return safeApiCall {
            ApiService.getApiHandler().login(map)
        }
    }
}