package com.example.mywishes.restApi

data class ApiResponse<T>(
    val data: T?,
    val message: String,
    val status: Boolean,
    val status_code: Boolean
)
