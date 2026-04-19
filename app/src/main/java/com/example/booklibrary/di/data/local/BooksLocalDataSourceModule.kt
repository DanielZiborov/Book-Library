package com.example.booklibrary.di.data.local

import com.example.booklibrary.data.datasources.local.BooksLocalDataSource
import com.example.booklibrary.data.datasources.local.BooksLocalDataSourceImpl
import com.example.booklibrary.data.datasources.local.db.BooksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BooksLocalDataSourceModule {

    @Provides
    @Singleton
    fun provideBooksLocalDataSource(booksDao: BooksDao): BooksLocalDataSource {
        return BooksLocalDataSourceImpl(booksDao)
    }
}