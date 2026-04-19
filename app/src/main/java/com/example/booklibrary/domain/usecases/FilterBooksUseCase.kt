package com.example.booklibrary.domain.usecases

import com.example.booklibrary.domain.BookRepository
import com.example.booklibrary.domain.entities.Status

class FilterBooksUseCase(private val bookRepository: BookRepository) {
    fun filterOf(status: Status) = bookRepository.filterOfStatus(status)
}