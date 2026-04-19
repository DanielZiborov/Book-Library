package com.example.booklibrary.domain

import com.example.booklibrary.core.network.NetworkResult
import com.example.booklibrary.domain.entities.BookEntity
import com.example.booklibrary.domain.entities.Status
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface BookRepository {
    fun getAllBooks(status: Status?, sortType: String?): Flow<List<BookEntity>>
    suspend fun refreshBooks(): NetworkResult<Unit>
    suspend fun upsertBook(
        book: BookEntity
    )
    suspend fun deleteBook(id: UUID)
}
