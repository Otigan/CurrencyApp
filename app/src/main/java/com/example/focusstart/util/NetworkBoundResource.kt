package com.example.focusstart.util

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map {
                Resource.Success(it)
            }
        } catch (t: Throwable) {
            query().map {
                Resource.Error(t, it)
            }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)

}