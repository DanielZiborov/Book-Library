package com.example.booklibrary.data.datasources.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.booklibrary.data.models.BookModel

@Database(
    entities = [
        BookModel::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BooksDb: RoomDatabase() {
    abstract val dao: BooksDao
}