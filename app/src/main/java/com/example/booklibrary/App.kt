package com.example.booklibrary

import android.app.Application
import com.example.booklibrary.data.datasources.local.db.BooksDb

class App: Application() {
    val booksDataBase by lazy {
        BooksDb.createDatabase(this)
    }
}