package com.example.booklibrary.domain.usecases

import com.example.booklibrary.core.network.NetworkResult
import com.example.booklibrary.core.usecases.SuspendUseCase
import com.example.booklibrary.domain.BookRepository

class RefreshBooksUseCase(
    private val bookRepository: BookRepository
): SuspendUseCase<NetworkResult<Unit>,Unit> {

    override suspend fun invoke(params: Unit): NetworkResult<Unit> {
        return bookRepository.refreshBooks()
    }
}
