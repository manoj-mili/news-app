package com.mili.news.data

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.mili.news.data.entities.NewsArticle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response
import java.lang.Exception


abstract class NetworkBoundResource<DbCache, NetworkRequest> {
    @ExperimentalCoroutinesApi
    fun asFlow() = flow {

        val dbValue = loadFromDb().first()
        emit(ViewState.loading(dbValue))
        if (shouldFetch(dbValue)) {
            emit(ViewState.success(dbValue))
            try {
                var dataExist = false
                if (dbValue is List<*> && dbValue.size > 0) {
                    dataExist = true
                }
                val apiResponse = fetchFromNetwork(dataExist)
                when {
                    apiResponse.isSuccessful && apiResponse.body() != null -> {
                        apiResponse.body()?.let { response ->
                            saveNetworkResult(response)
                        }
                        emitAll(loadFromDb().map { dbData -> ViewState.success(dbData) })
                    }
                    else -> {
                        emit(ViewState.error(apiResponse.message()))
                    }
                }
            } catch (e: Exception) {
                emit(ViewState.error(e.localizedMessage))
            }
        } else {
            emitAll(loadFromDb().map { ViewState.success(it) })
        }
    }

    @WorkerThread
    protected abstract suspend fun saveNetworkResult(response: NetworkRequest)

    @MainThread
    protected abstract fun shouldFetch(data: DbCache?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): Flow<DbCache>

    @MainThread
    protected abstract suspend fun fetchFromNetwork(dataExist: Boolean): Response<NetworkRequest>

}