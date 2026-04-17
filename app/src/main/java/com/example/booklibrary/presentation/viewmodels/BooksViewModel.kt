package com.example.booklibrary.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibrary.core.network.NetworkResult
import com.example.booklibrary.domain.entities.Status
import com.example.booklibrary.domain.usecases.AddBookUseCase
import com.example.booklibrary.domain.usecases.DeleteBookUseCase
import com.example.booklibrary.domain.usecases.GetBooksUseCase
import com.example.booklibrary.domain.usecases.RedactionBookUseCase
import com.example.booklibrary.domain.usecases.RefreshBooksUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID

class BooksViewModel(
    private val addBookUseCase: AddBookUseCase,
    private val deleteBookUseCase: DeleteBookUseCase,
    private val getBooksUseCase: GetBooksUseCase,
    private val redactionBookUseCase: RedactionBookUseCase,
    private val refreshBooksUseCase: RefreshBooksUseCase
) : ViewModel() {
    private val _isRefreshing = mutableStateOf(false)
    val isRefreshing = _isRefreshing

    private val _isLoading = mutableStateOf(true)
    val isLoading = _isLoading

    private val _uiEvent = Channel<String>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val booksState = getBooksUseCase.getBooks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        refreshBooks()
        Log.d("DEATH_AND_LIVE", "Я родился, если ты повернул и я ещё раз родился, значит я умирал!")
    }

    fun refreshBooks() {
        viewModelScope.launch {
            _isRefreshing.value = true

            when (val resultOfRefresh = refreshBooksUseCase.refreshBooks() ) {
                is NetworkResult.Success -> {}

                is NetworkResult.Error -> {
                    _uiEvent.send(resultOfRefresh.message)
                }
            }

            _isRefreshing.value = false
            _isLoading.value = false
        }
    }

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
