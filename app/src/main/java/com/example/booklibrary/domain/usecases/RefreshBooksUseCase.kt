package com.example.booklibrary.domain.usecases

import com.example.booklibrary.core.network.NetworkResult
import com.example.booklibrary.domain.BookRepository

class RefreshBooksUseCase(private val bookRepository: BookRepository) {
    suspend fun refreshBooks(): NetworkResult<Unit> {
        return bookRepository.refreshBooks()
    }
}
