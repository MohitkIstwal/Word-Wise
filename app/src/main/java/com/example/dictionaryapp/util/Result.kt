package com.example.dictionaryapp.util

sealed class Results<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : Results<T>(data)

    class Error<T>(message: String) : Results<T>(null, message)

    class Loading<T>(val isLoading: Boolean = true) : Results<T>(null)
}