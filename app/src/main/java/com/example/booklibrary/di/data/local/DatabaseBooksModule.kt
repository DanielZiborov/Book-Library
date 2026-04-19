package com.example.booklibrary.di.data.local

import android.content.Context
import androidx.room.Room
import com.example.booklibrary.data.datasources.local.db.BooksDao
import com.example.booklibrary.data.datasources.local.db.BooksDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseBooksModule {

    @Provides
    @Singleton
    fun provideBooksDatabase(
        @ApplicationContext context: Context
    ): BooksDb {
        return Room.databaseBuilder(
            context,
            BooksDb::class.java,
            "books.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBooksDao(db: BooksDb): BooksDao {
        return db.dao
    }
}