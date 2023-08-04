package com.example.pagingexample.data

sealed interface NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>
    data class Error(val error: String? = null) : NetworkResult<Nothing>
}