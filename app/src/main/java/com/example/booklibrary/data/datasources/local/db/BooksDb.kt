package com.example.booklibrary.data.datasources.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.booklibrary.data.models.BookModel

@Database(
    entities = [
        BookModel::class
    ],
    version = 1
)
abstract class BooksDb: RoomDatabase() {
    abstract val dao: BooksDao
    companion object {
        fun createDatabase(context: Context): BooksDb {
            return Room.databaseBuilder(
                context = context,
                klass = BooksDb::class.java,
                name = "books.db"
            ).build()
        }
    }
}