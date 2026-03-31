package com.example.booklibrary.domain.usecases

import com.example.booklibrary.domain.BookRepository
import java.util.UUID

class DeleteBookUseCase(private val bookRepository: BookRepository) {
    fun deleteBook(id: UUID) {
        bookRepository.deleteBook(id)
    }
}
