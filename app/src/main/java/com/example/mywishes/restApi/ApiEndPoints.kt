package com.example.mywishes.restApi

import com.example.mywishes.login.LoginResponse
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface ApiEndPoints {

    @POST("api/login")
    @FormUrlEncoded
    suspend fun login(@FieldMap map: HashMap<String,String>): ApiResponse<LoginResponse>

}