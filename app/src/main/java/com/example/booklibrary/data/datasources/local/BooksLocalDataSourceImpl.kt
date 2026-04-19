package com.example.booklibrary.data.datasources.local

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import com.example.booklibrary.data.datasources.local.db.BooksDao
import com.example.booklibrary.data.models.BookModel
import kotlinx.coroutines.flow.Flow

class BooksLocalDataSourceImpl(
    private val booksDao: BooksDao
): BooksLocalDataSource {

    override fun getBooksFromCache(): Flow<List<BookModel>> {
        return booksDao.getAllBooks()
    }

    override suspend fun upsertBooks(books: List<BookModel>) {
        booksDao.upsertBooks(books)
    }

    override suspend fun upsertBook(book: BookModel) {
        booksDao.upsertBook(book)
    }

    override suspend fun deleteBook(id: String) {
        booksDao.deleteBookById(id)
    }

    override fun filterOfStatus(status: String): Flow<List<BookModel>> {
        return booksDao.filterOfStatus(status)
    }

    override fun sortByYear(): Flow<List<BookModel>> {
        return booksDao.sortByYear()
    }

    override fun sortByRating(): Flow<List<BookModel>> {
        return booksDao.sortByRating()
    }
}