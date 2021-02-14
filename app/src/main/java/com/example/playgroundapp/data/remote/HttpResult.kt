package com.example.playgroundapp.data.remote

sealed class HttpResult<out T> {
    class Success<out T>(val data: T) : HttpResult<T>()
    class Error(val errorCode: Int, val message: String) : HttpResult<Nothing>()
}