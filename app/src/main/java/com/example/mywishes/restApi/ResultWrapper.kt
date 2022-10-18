package com.example.mywishes.restApi

sealed class ResultWrapper<out T> {
    //    val data: T? = null
    data class Success<out T>(val response: T?) : ResultWrapper<T?>()
    data class GenericError(val response: ApiResponse<*>? = null) : ResultWrapper<Nothing>()
}