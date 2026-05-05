package com.example.booklibrary.presentation.viewmodels

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
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID
import com.example.booklibrary.core.usecases.invoke
import com.example.booklibrary.domain.usecases.AddParams
import com.example.booklibrary.domain.usecases.GetStatisticUseCase
import com.example.booklibrary.domain.usecases.Parameters
import com.example.booklibrary.domain.usecases.RedactParams
import com.example.booklibrary.presentation.state.BookUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val addBookUseCase: AddBookUseCase,
    private val redactionBookUseCase: RedactionBookUseCase,
    private val deleteBookUseCase: DeleteBookUseCase,
    private val getBooksUseCase: GetBooksUseCase,
    private val refreshBooksUseCase: RefreshBooksUseCase,
    private val getStatisticUseCase: GetStatisticUseCase,
) : ViewModel() {

    private val _uiEvent = Channel<String>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiState = MutableStateFlow(BookUiState())
    val uiState = _uiState.asStateFlow()

    init {
        initUiState()
        refreshBooks()
    }

    private fun initUiState() {
        viewModelScope.launch {
            combine(
                _uiState,
                getBooksFlow(),
                getStatisticUseCase()
            ) { state, books, stat ->
                state.copy(
                    books = books,
                    statistic = stat
                )
            }.collect { newState ->
                _uiState.value = newState
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getBooksFlow() = combine(
        _uiState.map { it.currentSort },
        _uiState.map { it.currentStatus }
    ) { sort, status ->
        Parameters(status, sort)
    }.flatMapLatest { params ->
        getBooksUseCase(params)
    }

    fun refreshBooks() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isRefreshing = true)

            when (val resultOfRefresh = refreshBooksUseCase()) {
                is NetworkResult.Success -> {}

                is NetworkResult.Error -> {
                    _uiEvent.send(resultOfRefresh.message)
                }
            }

            _uiState.value = _uiState.value.copy(isRefreshing = false)
            _uiState.value = _uiState.value.copy(isLoading = false)
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
        viewModelScope.launch {
            addBookUseCase(
                AddParams(
                    nameOfBook = nameOfBook,
                    author = author,
                    year = year,
                    description = description,
                    rating = rating,
                    status = status,
                    startDate = startDate,
                    endDate = endDate
                )
            )
        }
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
        viewModelScope.launch {
            redactionBookUseCase(
                RedactParams(
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
            )
        }
    }

    fun deleteBook(id: UUID) {
        viewModelScope.launch {
            deleteBookUseCase(id)
        }
    }

    fun sortBy(typeOfSort: String) {
        _uiState.value = _uiState.value.copy(currentSort = typeOfSort)
    }

    fun filterOf(status: Status) {
        _uiState.value = _uiState.value.copy(currentStatus = status)
    }

    fun sortOff() {
        _uiState.value = _uiState.value.copy(currentSort = null)
    }

    fun filterOff() {
        _uiState.value = _uiState.value.copy(currentStatus = null)
    }
}