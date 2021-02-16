package com.example.playgroundapp.data.remote

import com.example.playgroundapp.domain.common.ErrorEntity
import com.example.playgroundapp.domain.common.Result
import com.google.gson.JsonParseException
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import java.net.HttpURLConnection

abstract class BaseRemoteDataSource {

    protected suspend fun <T> call(request: suspend () -> Response<T>): Result<T> {
        try {
            val response = request()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.Success(body)
            }
            return Result.Error(ErrorEntity.ApiError.Unknown(response.code(), response.message()))
        } catch (exception: Exception) {
            return Result.Error(parseError(exception))
        }
    }

    private fun parseError(ex: Exception): ErrorEntity {
        return when (ex) {
            is IOException -> ErrorEntity.ApiError.Network()
            is JsonParseException -> ErrorEntity.ApiError.JsonParsing()
            is HttpException -> when (ex.code()) {
                HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.ApiError.NotFound()
                // access denied
                HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.ApiError.AccessDenied()
                // unavailable service
                HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ApiError.ServiceUnavailable()
                else -> ErrorEntity.ApiError.Unknown(ex.code(), ex.message())
            }
            else -> ErrorEntity.ApiError.Unknown(-1, ex.message.orEmpty())
        }
    }
}