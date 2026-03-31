package com.example.booklibrary.domain.usecases

import com.example.booklibrary.domain.BookRepository
import com.example.booklibrary.domain.entities.BookEntity
import com.example.booklibrary.domain.entities.BookInfoEntity
import com.example.booklibrary.domain.entities.Status
import java.time.LocalDate
import java.util.UUID

class AddBookUseCase(private val bookRepository: BookRepository) {
    fun addBook(
        nameOfBook: String,
        author: String,
        year: Int,
        description: String,
        rating: Int,
        status: Status,
        startDate: LocalDate?,
        endDate: LocalDate?
    ) {
        bookRepository.addBook(
            BookInfoEntity(
                id = UUID.randomUUID(),
                rating = rating,
                status = status,
                startDate = startDate,
                endDate = endDate,
                bookEntity = BookEntity(
                    nameOfBook = nameOfBook,
                    author = author,
                    year = year,
                    description = description
                )
            )
        )
    }
}
