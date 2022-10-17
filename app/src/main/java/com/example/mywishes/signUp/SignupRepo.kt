package com.example.mywishes.signUp

import com.example.mywishes.login.LoginResponse
import com.example.mywishes.restApi.ApiResponse
import com.example.mywishes.restApi.ApiService

class SignupRepo {

    suspend fun doRegister(request: SignUpRequest): ApiResponse<SignUpResponse> {
        val map = HashMap<String, String>()
        map.put("email", request.email)
        map.put("password", request.password)
        map.put("number",request.mobile)
        map.put("name",request.name)
        map.put("dob","11-02-1998")

        return ApiService.getApiHandler().register(map)
    }


}