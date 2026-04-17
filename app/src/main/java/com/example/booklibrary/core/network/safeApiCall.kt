package com.example.booklibrary.core.network

import retrofit2.Response

suspend fun <T> safeApiCall(api: suspend () -> Response<T>): NetworkResult<T> {
    try {
        val response = api()
        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                return NetworkResult.Success(body)
            }
            return errorMessage("Body is empty")
        } else {
            return errorMessage("${response.code()} ${response.message()}")
        }
    } catch (e: Exception) {
        return errorMessage(e.message ?: "Unknown error")
    }
}

private fun <T> errorMessage(e: String): NetworkResult.Error<T> =
    NetworkResult.Error(data = null, message = "Api call failed: $e")
