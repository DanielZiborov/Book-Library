package com.example.booklibrary.data.datasources.local

import com.example.booklibrary.data.models.BookInfoModel
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface BooksLocalDataSource {
    fun getBooksFromCache(): Flow<List<BookInfoModel>>
    fun putBooksInCache(books: List<BookInfoModel>)
    fun addBookInCache(
        book: BookInfoModel
    )
    fun redactBookInCache(
        book: BookInfoModel
    )
    fun deleteBookInCache(id: UUID)
}