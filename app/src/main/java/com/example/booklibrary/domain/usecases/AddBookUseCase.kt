package com.example.booklibrary.domain.usecases

import com.example.booklibrary.core.usecases.SuspendUseCase
import com.example.booklibrary.domain.BookRepository
import com.example.booklibrary.domain.entities.BookEntity
import com.example.booklibrary.domain.entities.Status
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject

class AddBookUseCase @Inject constructor(
    private val bookRepository: BookRepository
) : SuspendUseCase<Unit, AddParams> {

    override suspend fun invoke(params: AddParams) {
        bookRepository.upsertBook(
            BookEntity(
                author = params.author,
                description = params.description,
                endDate = params.endDate,
                id = UUID.randomUUID(),
                rating = params.rating,
                status = params.status,
                startDate = params.startDate,
                title = params.nameOfBook,
                year = params.year
            )
        )
    }
}

data class AddParams(
    val nameOfBook: String,
    val author: String,
    val year: Int,
    val description: String,
    val rating: Int,
    val status: Status,
    val startDate: LocalDate?,
    val endDate: LocalDate?
)