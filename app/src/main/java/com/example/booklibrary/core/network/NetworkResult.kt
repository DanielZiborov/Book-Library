package com.example.booklibrary.core.network

sealed class NetworkResult<T> {
    class Success<T>(val data: T?): NetworkResult<T>()
    class Error<T>(val data: T?, val message: String): NetworkResult<T>()
}