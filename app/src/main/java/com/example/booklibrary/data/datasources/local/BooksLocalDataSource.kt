package com.example.booklibrary.data.datasources.local

import com.example.booklibrary.data.models.BookModel
import com.example.booklibrary.domain.entities.Statistic
import kotlinx.coroutines.flow.Flow

interface BooksLocalDataSource {
    fun getBooksFromCache(status: String?, sortType: String?): Flow<List<BookModel>>
    suspend fun upsertBooks(books: List<BookModel>)
    suspend fun upsertBook(
        book: BookModel
    )
    suspend fun deleteBook(id: String)
    fun getStatistic(): Flow<Statistic>
    suspend fun isReadingBooks(): Boolean
}