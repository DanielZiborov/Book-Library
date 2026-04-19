package com.example.booklibrary.data.datasources.remote.service

import com.example.booklibrary.data.models.BookData
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface BooksApiService {
    @GET("books")
    suspend fun getBooks(): Response<BookData>
}
