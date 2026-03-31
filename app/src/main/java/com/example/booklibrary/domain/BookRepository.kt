package com.example.booklibrary.domain

import com.example.booklibrary.domain.entities.BookInfoEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface BookRepository {
    fun getAllBooks(): Flow<List<BookInfoEntity>>
    fun addBook(
        book: BookInfoEntity
    )
    fun redactionBook(
        book: BookInfoEntity
    )
    fun deleteBook(id: UUID)
}
