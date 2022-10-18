package com.example.mywishes.signUp

import com.example.mywishes.base.BaseRepository
import com.example.mywishes.restApi.ApiResponse
import com.example.mywishes.restApi.ApiService
import com.example.mywishes.restApi.ResultWrapper
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class SignupRepo : BaseRepository(){

    suspend fun doRegister(request: SignUpRequest, file: File): ResultWrapper<ApiResponse<SignUpResponse>?> {
        val map = HashMap<String, String>()
        map.put("email", request.email)
        map.put("password", request.password)
        map.put("mobile",request.mobile)
        map.put("name",request.name)
        map.put("dob","11-02-1998")

        val image = MultipartBody.Part.createFormData(
            name = "XYS",
            filename = file.name,
            body = file.asRequestBody()
        )

        return safeApiCall {
            ApiService.getApiHandler().register(map, image)
        }
    }



}