package com.example.booklibrary.data

import com.example.booklibrary.core.network.NetworkResult
import com.example.booklibrary.data.datasources.local.BooksLocalDataSource
import com.example.booklibrary.data.datasources.remote.BooksRemoteDataSource
import com.example.booklibrary.data.mappers.toEntity
import com.example.booklibrary.data.mappers.toModel
import com.example.booklibrary.data.mappers.toStatusModel
import com.example.booklibrary.domain.BookRepository
import com.example.booklibrary.domain.entities.BookEntity
import com.example.booklibrary.domain.entities.Status
import kotlinx.coroutines.flow.Flow
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
                if(result.data != null){
                    booksLocalDataSource.upsertBooks(result.data)
                }
                NetworkResult.Success(Unit)
            }

            is NetworkResult.Error -> {
                NetworkResult.Error(Unit,result.message)
            }
        }
    }

    override suspend fun upsertBook(
        book: BookEntity
    ) {
        booksLocalDataSource.upsertBook(
            book.toModel()
        )
    }

    override suspend fun deleteBook(id: UUID) {
        booksLocalDataSource.deleteBook(id.toString())
    }

    override fun sortByYear(): Flow<List<BookEntity>> {
        return booksLocalDataSource.sortByYear()
            .map{list->
                list.map{it.toEntity()}
            }
    }

    override fun sortByRating(): Flow<List<BookEntity>> {
        return booksLocalDataSource.sortByRating()
            .map{list->
                list.map{it.toEntity()}
            }
    }

    override fun filterOfStatus(status: Status): Flow<List<BookEntity>> {
        return booksLocalDataSource.filterOfStatus(status.toStatusModel())
            .map{list->
                list.map{it.toEntity()}
            }
    }
}
