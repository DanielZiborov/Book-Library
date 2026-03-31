package com.example.booklibrary.domain.usecases

import com.example.booklibrary.domain.BookRepository
import com.example.booklibrary.domain.entities.BookEntity
import com.example.booklibrary.domain.entities.BookInfoEntity
import com.example.booklibrary.domain.entities.Status
import java.time.LocalDate
import java.util.UUID

class RedactionBookUseCase(private val bookRepository: BookRepository) {
    fun redactionBook(
        id: UUID,
        nameOfBook: String,
        author: String,
        year: Int,
        description: String,
        rating: Int,
        status: Status,
        startDate: LocalDate?,
        endDate: LocalDate?
    ) {
        bookRepository.redactionBook(
            BookInfoEntity(
                id = id,
                rating = rating,
                status = status,
                startDate = startDate,
                endDate = endDate,
                bookEntity = BookEntity(
                    nameOfBook = nameOfBook,
                    author = author,
                    year = year,
                    description = description
                ),
            )
        )
    }
}
