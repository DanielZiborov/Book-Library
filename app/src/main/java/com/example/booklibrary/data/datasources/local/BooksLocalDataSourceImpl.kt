package com.example.booklibrary.data.datasources.local

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import com.example.booklibrary.data.models.BookModel
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

    private val booksFlow = MutableStateFlow<List<BookModel>>(emptyList())

    init {
        val json = prefs.getString("books_json", null)

        val books: List<BookModel> = if (json != null) {
            val type = object : TypeToken<List<BookModel>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }

        booksFlow.value = books
    }

    override fun getBooksFromCache(): Flow<List<BookModel>> = booksFlow

    private fun saveBooks(books: List<BookModel>) {
        val json = gson.toJson(books)

        prefs.edit {
            putString("books_json", json)
        }

        booksFlow.value = books
    }

    override fun putBooksInCache(books: List<BookModel>) {

        val resultsBooks = booksFlow.value.map { oldBook ->
            var resultBook = oldBook
            books.forEach { newBook ->
                if (oldBook != newBook &&
                    oldBook.id == newBook.id
                ) {
                    resultBook = newBook
                }
            }
            resultBook
        }.toMutableList()

        val newBooks = books.filterNot { it in resultsBooks }

        resultsBooks+=newBooks
        saveBooks(resultsBooks)
    }

    override fun addBookInCache(
        book: BookModel
    ) {
        val updated = booksFlow.value + book
        saveBooks(updated)
    }

    override fun redactBookInCache(
        book: BookModel
    ) {
        val updated = booksFlow.value.map {
            if (it.id == book.id) {
                book
            } else it
        }
        saveBooks(updated)
    }

    override fun deleteBookInCache(id: UUID) {
        val updated = booksFlow.value.filterNot { it.id == id.toString() }
        saveBooks(updated)
    }
}
