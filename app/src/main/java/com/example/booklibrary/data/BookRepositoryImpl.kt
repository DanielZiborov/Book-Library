package com.example.booklibrary.data

import com.example.booklibrary.core.network.NetworkResult
import com.example.booklibrary.data.datasources.local.BooksLocalDataSource
import com.example.booklibrary.data.datasources.remote.BooksRemoteDataSource
import com.example.booklibrary.data.mappers.toEntity
import com.example.booklibrary.data.mappers.toModel
import com.example.booklibrary.data.models.BookModel
import com.example.booklibrary.domain.BookRepository
import com.example.booklibrary.domain.entities.BookEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.util.UUID

class BookRepositoryImpl(
    val booksLocalDataSource: BooksLocalDataSource,
    val booksRemoteDataSource: BooksRemoteDataSource
) : BookRepository {

    override fun getAllBooks(): Flow<List<BookEntity>> {
        return booksLocalDataSource.getBooksFromCache()
            .map { list ->
                list.map { it.toEntity() }
            }
    }

    override suspend fun refreshBooks(): NetworkResult<Unit> {

        return when (val result = booksRemoteDataSource.getBooksFromRemote()) {

            is NetworkResult.Success -> {
                booksLocalDataSource.putBooksInCache(result.data?:emptyList())
                NetworkResult.Success(Unit)
            }

            is NetworkResult.Error -> {
                NetworkResult.Error(Unit,result.message)
            }
        }
    }

    override fun addBook(
        book: BookEntity
    ) {
        booksLocalDataSource.addBookInCache(
            book.toModel()
        )
    }

    override fun redactionBook(
        book: BookEntity
    ) {
        booksLocalDataSource.redactBookInCache(
            book.toModel()
        )
    }

    override fun deleteBook(id: UUID) {
        booksLocalDataSource.deleteBookInCache(id)
    }
}
