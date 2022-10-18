package com.example.mywishes.restApi

import com.example.mywishes.login.LoginResponse
import com.example.mywishes.signUp.SignUpResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiEndPoints {

    @POST("api/login")
    @FormUrlEncoded
    suspend fun login(@FieldMap map: HashMap<String, String>): ApiResponse<LoginResponse>

    @Multipart
    @POST("api/register")
    suspend fun register(@PartMap map: HashMap<String, String>,
                         @Part  profilePhot: MultipartBody.Part): ApiResponse<SignUpResponse>

}