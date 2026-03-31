package com.example.booklibrary.domain.usecases

import com.example.booklibrary.domain.BookRepository

class GetBooksUseCase(private val bookRepository: BookRepository) {
    fun getBooks() = bookRepository.getAllBooks()
}
