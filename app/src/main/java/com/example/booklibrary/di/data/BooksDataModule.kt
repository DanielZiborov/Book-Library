package com.example.booklibrary.di.data

import com.example.booklibrary.data.BookRepositoryImpl
import com.example.booklibrary.data.datasources.local.BooksLocalDataSource
import com.example.booklibrary.data.datasources.local.BooksLocalDataSourceImpl
import com.example.booklibrary.data.datasources.remote.BooksRemoteDataSource
import com.example.booklibrary.data.datasources.remote.BooksRemoteDataSourceImpl
import com.example.booklibrary.domain.BookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BooksDataModule {

    @Binds
    @Singleton
    abstract fun bindBookRepository(
        impl: BookRepositoryImpl
    ): BookRepository

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        impl: BooksLocalDataSourceImpl
    ): BooksLocalDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        impl: BooksRemoteDataSourceImpl
    ): BooksRemoteDataSource
}