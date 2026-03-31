package com.example.booklibrary.data

import com.example.booklibrary.data.datasources.local.BooksLocalDataSource
import com.example.booklibrary.data.mappers.toEntity
import com.example.booklibrary.data.mappers.toModel
import com.example.booklibrary.domain.BookRepository
import com.example.booklibrary.domain.entities.BookInfoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID

class BookRepositoryImpl(
    val booksLocalDataSource: BooksLocalDataSource
) : BookRepository {

    override fun getAllBooks(): Flow<List<BookInfoEntity>> {
        return booksLocalDataSource.getBooksFromCache()
            .map { list ->
                list.map { it.toEntity() }
            }
    }

    override fun addBook(
        book: BookInfoEntity
    ) {
        booksLocalDataSource.addBookInCache(
            book.toModel()
        )
    }

    override fun redactionBook(
        book: BookInfoEntity
    ) {
        booksLocalDataSource.redactBookInCache(
            book.toModel()
        )
    }

    override fun deleteBook(id: UUID) {
        booksLocalDataSource.deleteBookInCache(id)
    }
}
