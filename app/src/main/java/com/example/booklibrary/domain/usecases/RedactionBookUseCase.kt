package com.example.booklibrary.domain.usecases

import com.example.booklibrary.core.usecases.SuspendUseCase
import com.example.booklibrary.domain.BookRepository
import com.example.booklibrary.domain.entities.BookEntity
import com.example.booklibrary.domain.entities.Status
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject

class RedactionBookUseCase @Inject constructor(
    private val bookRepository: BookRepository
): SuspendUseCase<Unit,RedactParams> {

    override suspend fun invoke(params: RedactParams) {
        bookRepository.upsertBook(
            BookEntity(
                author = params.author,
                description = params.description,
                endDate = params.endDate,
                id = params.id,
                rating = params.rating,
                status = params.status,
                startDate = params.startDate,
                title = params.nameOfBook,
                year = params.year
            )
        )
    }
}

data class RedactParams(
    val id: UUID,
    val nameOfBook: String,
    val author: String,
    val year: Int,
    val description: String,
    val rating: Int,
    val status: Status,
    val startDate: LocalDate?,
    val endDate: LocalDate?
)