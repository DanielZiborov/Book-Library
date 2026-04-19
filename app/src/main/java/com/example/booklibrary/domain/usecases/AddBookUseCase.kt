package com.example.booklibrary.domain.usecases

import com.example.booklibrary.domain.BookRepository
import com.example.booklibrary.domain.entities.BookEntity
import com.example.booklibrary.domain.entities.Status
import java.time.LocalDate
import java.util.UUID

class AddBookUseCase(private val bookRepository: BookRepository) {
    suspend fun addBook(
        nameOfBook: String,
        author: String,
        year: Int,
        description: String,
        rating: Int,
        status: Status,
        startDate: LocalDate?,
        endDate: LocalDate?
    ) {
        bookRepository.upsertBook(
            BookEntity(
                author = author,
                description = description,
                endDate = endDate,
                id = UUID.randomUUID(),
                rating = rating,
                status = status,
                startDate = startDate,
                title = nameOfBook,
                year = year
            )
        )
    }
}