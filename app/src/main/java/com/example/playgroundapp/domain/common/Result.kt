package com.example.playgroundapp.domain.common

sealed class Result<out T> {
    class Success<out T>(val data: T) : Result<T>()
    class Error<out T>(val error: ErrorEntity) : Result<T>()
}

sealed class ErrorEntity {
    sealed class ApiError : ErrorEntity() {
        class Network : ApiError()
        class AccessDenied : ApiError()
        class ServiceUnavailable : ApiError()
        class JsonParsing : ApiError()
        class NotFound : ApiError()
        class Unknown(val code: Int, val message: String) : ApiError()
    }
}