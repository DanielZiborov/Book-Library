package com.example.booklibrary.di.data.remote

import com.example.booklibrary.data.datasources.remote.BooksRemoteDataSource
import com.example.booklibrary.data.datasources.remote.BooksRemoteDataSourceImpl
import com.example.booklibrary.data.datasources.remote.service.BooksApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BooksRemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideBooksRemoteDataSource(
        booksApiService: BooksApiService
    ): BooksRemoteDataSource {
        return BooksRemoteDataSourceImpl(booksApiService)
    }
}