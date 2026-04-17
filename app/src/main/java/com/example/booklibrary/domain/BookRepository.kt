package com.example.booklibrary.domain

import com.example.booklibrary.core.network.NetworkResult
import com.example.booklibrary.domain.entities.BookEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface BookRepository {
    fun getAllBooks(): Flow<List<BookEntity>>
    suspend fun refreshBooks(): NetworkResult<Unit>
    fun addBook(
        book: BookEntity
    )
    fun redactionBook(
        book: BookEntity
    )
    fun deleteBook(id: UUID)
}
