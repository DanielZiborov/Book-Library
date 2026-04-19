package com.example.booklibrary.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibrary.core.network.NetworkResult
import com.example.booklibrary.domain.entities.Status
import com.example.booklibrary.domain.usecases.AddBookUseCase
import com.example.booklibrary.domain.usecases.DeleteBookUseCase
import com.example.booklibrary.domain.usecases.FilterBooksUseCase
import com.example.booklibrary.domain.usecases.GetBooksUseCase
import com.example.booklibrary.domain.usecases.RedactionBookUseCase
import com.example.booklibrary.domain.usecases.RefreshBooksUseCase
import com.example.booklibrary.domain.usecases.SortBooksUseCase
import com.example.booklibrary.domain.usecases.TypeOfSort
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID

class BooksViewModel(
    private val addBookUseCase: AddBookUseCase,
    private val redactionBookUseCase: RedactionBookUseCase,
    private val deleteBookUseCase: DeleteBookUseCase,
    private val getBooksUseCase: GetBooksUseCase,
    private val refreshBooksUseCase: RefreshBooksUseCase,
    private val filterBooksUseCase: FilterBooksUseCase,
    private val sortBooksUseCase: SortBooksUseCase
) : ViewModel() {
    private val _isRefreshing = mutableStateOf(false)
    val isRefreshing = _isRefreshing

    private val _isLoading = mutableStateOf(true)
    val isLoading = _isLoading

    private val _uiEvent = Channel<String>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _currentSort = MutableStateFlow<TypeOfSort?>(null)
    private val _currentStatus = MutableStateFlow<Status?>(null)

//    @OptIn(ExperimentalCoroutinesApi::class)
//    val booksState = combine(_currentSort, _currentStatus) { sort, status ->
//        sort to status
//    }.flatMapLatest { (sort, status) ->
//        when {
//            status != null -> filterBooksUseCase.filterOf(status)
//            sort == TypeOfSort.RATING -> sortBooksUseCase.sortBy(TypeOfSort.RATING)
//            sort == TypeOfSort.YEAR -> sortBooksUseCase.sortBy(TypeOfSort.YEAR)
//            else -> getBooksUseCase.getBooks()
//        }
//    }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000),
//        initialValue = emptyList()
//    )

    val booksState = getBooksUseCase.getBooks()
        .stateIn(
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

            when (val resultOfRefresh = refreshBooksUseCase.refreshBooks()) {
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
    }

    fun deleteBook(id: UUID) {
        viewModelScope.launch {
            deleteBookUseCase.deleteBook(id)
        }
    }

    fun sortBy(typeOfSort: TypeOfSort) {
        _currentSort.value = typeOfSort
    }

    fun sortOff() {
        _currentSort.value = null
    }

    fun filterOf(status: Status) {
        _currentStatus.value = status
    }

    fun filterOff() {
        _currentStatus.value = null
    }
}
