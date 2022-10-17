package com.example.mywishes.restApi

import com.example.mywishes.login.LoginResponse
import com.example.mywishes.signUp.SignUpResponse
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiEndPoints {

    @POST("api/login")
    @FormUrlEncoded
    suspend fun login(@FieldMap map: HashMap<String, String>): ApiResponse<LoginResponse>

    @POST("api/register")
    @FormUrlEncoded
    suspend fun register(@FieldMap map: HashMap<String, String>): ApiResponse<SignUpResponse>

}