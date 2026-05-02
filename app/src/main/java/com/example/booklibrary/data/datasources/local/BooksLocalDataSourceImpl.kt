package com.example.booklibrary.data.datasources.local

import com.example.booklibrary.data.datasources.local.db.BooksDao
import com.example.booklibrary.data.models.BookModel
import com.example.booklibrary.domain.entities.Statistic
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BooksLocalDataSourceImpl @Inject constructor(
    private val booksDao: BooksDao
): BooksLocalDataSource {

    override fun getBooksFromCache(status: String?, sortType: String?): Flow<List<BookModel>> {
        return booksDao.getAllBooks(status,sortType)
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

    override fun getStatistic(): Flow<Statistic> {
        return booksDao.getStatistic()
    }

    override suspend fun isReadingBooks(): Boolean {
        return booksDao.isReadingBooks()
    }
}