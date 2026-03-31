package com.example.booklibrary.data.datasources.local

import android.content.Context
import androidx.core.content.edit
import com.example.booklibrary.data.models.BookInfoModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID

class BooksLocalDataSourceImpl(
    private val context: Context
) : BooksLocalDataSource {

    private val prefs = context.getSharedPreferences("books_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    private val booksFlow = MutableStateFlow<List<BookInfoModel>>(emptyList())

    init {
        val json = prefs.getString("books_json", null)
        val books: List<BookInfoModel> = if (json != null) {
            val type = object : TypeToken<List<BookInfoModel>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }

        booksFlow.value = books
    }

    override fun getBooksFromCache(): Flow<List<BookInfoModel>> = booksFlow

    override fun putBooksInCache(books: List<BookInfoModel>) {
        val json = gson.toJson(books)
        prefs.edit {
            putString("books_json", json)
        }
    }

    override fun addBookInCache(
        book: BookInfoModel
    ) {
        val updated = booksFlow.value + book
        booksFlow.value = updated
        putBooksInCache(updated)
    }

    override fun redactBookInCache(
        book: BookInfoModel
    ) {
        val updated = booksFlow.value.map {
            if (it.id == book.id) {
                book
            } else it
        }

        booksFlow.value = updated
        putBooksInCache(updated)
    }

    override fun deleteBookInCache(id: UUID) {
        val updated = booksFlow.value.filterNot { it.id == id }
        booksFlow.value = updated
        putBooksInCache(updated)
    }
}