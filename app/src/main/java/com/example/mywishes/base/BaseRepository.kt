package com.example.mywishes.base

import com.example.mywishes.restApi.ApiResponse
import com.example.mywishes.restApi.ResultWrapper
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

open class BaseRepository {

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO, apiCall: suspend () -> T
    ): ResultWrapper<T?> {
        return withContext(dispatcher) {
            try {

                val response = apiCall.invoke()

                if (response is ApiResponse<*>) {

                    if (response.status) {
                        ResultWrapper.Success(response as T)
                    } else {
                        ResultWrapper.GenericError(response)
                    }

                } else if (response is Response<*> && !response.isSuccessful) {
                    val errorResponse = Gson().fromJson(
                        response.errorBody().toString(), ApiResponse::class.java
                    )
                    ResultWrapper.GenericError(errorResponse)
                } else {
                    val errorResponse = getDefaultErrorObject()
                    ResultWrapper.GenericError(errorResponse)
                }

            } catch (throwable: Throwable) {

                when (throwable) {
                    is SocketTimeoutException, is SocketException, is ConnectException, is TimeoutException, is UnknownHostException -> {
                        val errorResponse = getDefaultErrorObject()
                        ResultWrapper.GenericError(errorResponse)
                    }

                    is HttpException -> {
                        val errorResponse = getDefaultErrorObject()
                        ResultWrapper.GenericError(errorResponse)
                    }

                    else -> {
                        val errorResponse = getDefaultErrorObject()
                        ResultWrapper.GenericError(errorResponse)
                    }
                }
            }
        }
    }

    private fun getDefaultErrorObject(): ApiResponse<*> {
        return ApiResponse<Any>(status = false, data = null, message = "ERROR", status_code = false)
    }
}