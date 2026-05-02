package com.example.booklibrary.domain.usecases

import com.example.booklibrary.core.usecases.SuspendUseCase
import com.example.booklibrary.domain.BookRepository
import javax.inject.Inject

class IsReadingBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository
) : SuspendUseCase<Boolean, Unit> {
    override suspend fun invoke(params: Unit): Boolean {
        return bookRepository.isReadingBooks()
    }
}