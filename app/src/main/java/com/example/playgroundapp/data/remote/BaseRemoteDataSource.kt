package com.example.playgroundapp.data.remote

import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Exception

abstract class BaseRemoteDataSource {

    protected suspend fun <T> call(request: suspend () -> Response<T>): HttpResult<T> {
        try {
            val response = request()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return HttpResult.Success(body)
            }
            return HttpResult.Error(response.code(), response.message())
        } catch (exception: Exception) {
            return HttpResult.Error(-1, exception.message.orEmpty())
        }
    }
}