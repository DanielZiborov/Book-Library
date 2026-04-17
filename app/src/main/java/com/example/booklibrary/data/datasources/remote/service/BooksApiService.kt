package com.example.booklibrary.data.datasources.remote.service

import com.example.booklibrary.data.models.BookData
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://api-labs.wiremockapi.cloud"
private val CONTENT_TYPE = "application/json".toMediaType()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory(CONTENT_TYPE))
    .baseUrl(BASE_URL)
    .build()

interface BooksApiService {
    @GET("books")
    suspend fun getBooks(): Response<BookData>
}

object BooksApi {
    val booksApiService: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }
}
