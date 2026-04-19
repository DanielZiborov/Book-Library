package com.example.booklibrary.domain.usecases

import com.example.booklibrary.domain.BookRepository
import com.example.booklibrary.domain.entities.BookEntity
import kotlinx.coroutines.flow.Flow

class SortBooksUseCase(
    private val bookRepository: BookRepository
) {
    fun sortBy(typeOfSort: TypeOfSort): Flow<List<BookEntity>> {
        return when(typeOfSort){
            TypeOfSort.YEAR -> bookRepository.sortByYear()
            TypeOfSort.RATING -> bookRepository.sortByRating()
        }
    }
}

enum class TypeOfSort {
    YEAR,
    RATING
}