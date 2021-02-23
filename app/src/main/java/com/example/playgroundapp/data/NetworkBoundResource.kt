package com.example.playgroundapp.data

import com.example.playgroundapp.domain.common.ErrorEntity
import com.example.playgroundapp.domain.common.Result
import kotlinx.coroutines.flow.*

sealed class Resource<out T>(val data: T? = null) {
    class Success<out T>(data: T) : Resource<T>(data)
    class Loading<out T>(data: T? = null) : Resource<T>(data)
    class Error<out T>(val errorEntity: ErrorEntity, data: T? = null) : Resource<T>(data)
}

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())

        val dbSource = fetchFromDatabase().first()

        if (shouldFetchRemoteData(dbSource)) {

            when (val apiResponse = fetchFromRemote()) {
                is Result.Success -> {
                    saveRemoteResults(apiResponse.data)
                    emitAll(fetchFromDatabase().map { Resource.Success(it) })
                }
                is Result.Error -> {
                    onRemoteFetchFailed()
                    emit(Resource.Error(apiResponse.error, dbSource))
                }
            }
        } else {
            emitAll(fetchFromDatabase().map { Resource.Success(it) })
        }
    }

    protected open fun onRemoteFetchFailed() {}

    protected abstract fun fetchFromDatabase(): Flow<ResultType>

    protected abstract fun shouldFetchRemoteData(data: ResultType?): Boolean

    protected abstract suspend fun fetchFromRemote(): Result<RequestType>

    protected abstract suspend fun saveRemoteResults(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}