package com.example.booklibrary.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibrary.domain.entities.Status
import com.example.booklibrary.domain.usecases.AddBookUseCase
import com.example.booklibrary.domain.usecases.DeleteBookUseCase
import com.example.booklibrary.domain.usecases.GetBooksUseCase
import com.example.booklibrary.domain.usecases.RedactionBookUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import java.util.UUID

class BooksViewModel(
    private val addBookUseCase: AddBookUseCase,
    private val deleteBookUseCase: DeleteBookUseCase,
    private val getBooksUseCase: GetBooksUseCase,
    private val redactionBookUseCase: RedactionBookUseCase
) : ViewModel() {

    val booksState = getBooksUseCase.getBooks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

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
        addBookUseCase.addBook(
            nameOfBook = nameOfBook,
            author = author,
            year = year,
            description = description,
            rating = rating,
            status = status,
            startDate = startDate,
            endDate = endDate
        )
    }

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
        redactionBookUseCase.redactionBook(
            id = id,
            nameOfBook = nameOfBook,
            author = author,
            year = year,
            description = description,
            rating = rating,
            status = status,
            startDate = startDate,
            endDate = endDate
        )
    }

    fun deleteBook(id: UUID) {
        deleteBookUseCase.deleteBook(id)
    }
}