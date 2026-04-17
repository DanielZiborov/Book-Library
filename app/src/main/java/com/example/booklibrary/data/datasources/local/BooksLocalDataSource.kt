package com.example.booklibrary.data.datasources.local

import com.example.booklibrary.data.models.BookModel
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface BooksLocalDataSource {
    fun getBooksFromCache(): Flow<List<BookModel>>
    fun putBooksInCache(books: List<BookModel>)
    fun addBookInCache(
        book: BookModel
    )
    fun redactBookInCache(
        book: BookModel
    )
    fun deleteBookInCache(id: UUID)
}