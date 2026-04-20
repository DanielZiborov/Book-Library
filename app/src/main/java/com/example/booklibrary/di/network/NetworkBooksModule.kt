package com.example.booklibrary.di.network

import com.example.booklibrary.data.datasources.remote.service.BooksApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkBooksModule {
    private val BASE_URL = "https://api-labs.wiremockapi.cloud"
    private val CONTENT_TYPE = "application/json".toMediaType()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(Json.Default.asConverterFactory(CONTENT_TYPE))
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideBooksApi(retrofit: Retrofit): BooksApiService {
        return retrofit.create(BooksApiService::class.java)
    }
}