package com.example.mywishes.restApi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {

    private val baseUrl = "https://www.thedatavue.com/mywishes/public/"

    private val loggingIntercepter = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val httpClient = OkHttpClient.Builder().addInterceptor(loggingIntercepter).build()


    private fun getInstance(): Retrofit {
        return Retrofit.Builder().client(httpClient).baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiHandler(): ApiEndPoints{
        return getInstance().create(ApiEndPoints::class.java)
    }


}