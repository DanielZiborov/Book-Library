package com.example.booklibrary.domain.usecases

import com.example.booklibrary.core.usecases.SuspendUseCase
import com.example.booklibrary.domain.BookRepository
import java.util.UUID
import javax.inject.Inject

class DeleteBookUseCase @Inject constructor(
    private val bookRepository: BookRepository
): SuspendUseCase<Unit, UUID> {

    override suspend fun invoke(params: UUID) {
        bookRepository.deleteBook(params)
    }
}
