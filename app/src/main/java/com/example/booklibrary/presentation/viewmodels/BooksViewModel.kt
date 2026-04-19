package com.example.booklibrary.presentation.viewmodels

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
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID
import com.example.booklibrary.core.usecases.invoke
import com.example.booklibrary.domain.usecases.AddParams
import com.example.booklibrary.domain.usecases.Parameters
import com.example.booklibrary.domain.usecases.RedactParams
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class BooksViewModel(
    private val addBookUseCase: AddBookUseCase,
    private val redactionBookUseCase: RedactionBookUseCase,
    private val deleteBookUseCase: DeleteBookUseCase,
    private val getBooksUseCase: GetBooksUseCase,
    private val refreshBooksUseCase: RefreshBooksUseCase
) : ViewModel() {
    private val _isRefreshing = mutableStateOf(false)
    val isRefreshing = _isRefreshing

    private val _isLoading = mutableStateOf(true)
    val isLoading = _isLoading

    private val _uiEvent = Channel<String>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _currentSort = MutableStateFlow<String?>(null)
    private val _currentStatus = MutableStateFlow<Status?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val booksState = combine(
        _currentSort,
        _currentStatus
    ) { sort, status ->
        Parameters(status, sort)
    }.flatMapLatest { params ->
        getBooksUseCase(params)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        refreshBooks()
    }

    fun refreshBooks() {
        viewModelScope.launch {
            _isRefreshing.value = true

            when (val resultOfRefresh = refreshBooksUseCase()) {
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
        _currentSort.value = typeOfSort
    }

    fun filterOf(status: Status) {
        _currentStatus.value = status
    }

    fun sortOff() {
        _currentSort.value = null
    }

    fun filterOff() {
        _currentStatus.value = null
    }
}
