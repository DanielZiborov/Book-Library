package com.example.booklibrary.di.repository

import com.example.booklibrary.data.BookRepositoryImpl
import com.example.booklibrary.data.datasources.local.BooksLocalDataSource
import com.example.booklibrary.data.datasources.remote.BooksRemoteDataSource
import com.example.booklibrary.domain.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BooksRepositoryModule {

    @Provides
    @Singleton
    fun provideBooksRepository(
        booksLocalDataSource: BooksLocalDataSource,
        booksRemoteDataSource: BooksRemoteDataSource
    ): BookRepository {
        return BookRepositoryImpl(
            booksLocalDataSource = booksLocalDataSource,
            booksRemoteDataSource = booksRemoteDataSource
        )
    }

}