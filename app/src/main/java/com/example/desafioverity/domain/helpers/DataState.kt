package com.example.desafioverity.domain.helpers

sealed class DataState<out T> {
    data class Data<T>(
        val data: T
    ) : DataState<T>()

    data class Error(
        val error: Throwable
    ) : DataState<Nothing>()

    data class Loading(
        val loadingState: LoadingState = LoadingState.Idle
    ) : DataState<Nothing>()

    data class Limit(
        val limitState: LimitState = LimitState.Idle
    ) : DataState<Nothing>()

}

sealed class LoadingState {
    data object Loading : LoadingState()
    data object Idle : LoadingState()
}

sealed class LimitState {
    data object Limit : LimitState()
    data object Idle : LimitState()
}