package com.example.booklibrary.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.booklibrary.domain.entities.BookEntity
import com.example.booklibrary.domain.entities.Status
import com.example.booklibrary.presentation.state.BookFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate


class BookFormViewModel : ViewModel() {
    private val nowLocalDate = LocalDate.now()
    var state by mutableStateOf(BookFormState())
        private set

    fun onNameChange(value: String) {
        state = state.copy(
            nameOfBook = value,
            nameError = if (value.isNotBlank()) "" else "Empty name"
        )
    }

    fun onAuthorChange(value: String) {
        state = state.copy(
            author = value,
            authorError = if (value.isNotBlank()) "" else "Empty author"
        )
    }

    fun onYearChange(value: String) {
        state = state.copy(
            year = value,
            yearError = if (value.toIntOrNull() in 350..nowLocalDate.year) "" else "Invalid year"
        )
    }

    fun onDescriptionChange(value: String) {
        state = state.copy(description = value)
    }

    fun onRatingChange(value: Int) {
        if (state.rating == value) {
            state = state.copy(rating = 0)
        } else {
            state = state.copy(rating = value)
        }
    }

    fun onStatusChange(value: Status) {
        state = state.copy(status = value)
    }

    fun onStartDateChange(value: LocalDate?) {
        val dateReadingError  = validateDates(
            value,
            state.endDate
        )
        state = state.copy(
            startDate = value,
            dateReadingError = dateReadingError
        )
    }

    fun onEndDateChange(value: LocalDate?) {
        val dateReadingError  = validateDates(
            state.startDate,
            value
        )
        state = state.copy(
            endDate = value,
            dateReadingError = dateReadingError
        )
    }

    fun setInitial(book: BookEntity?) {
        book ?: return
        state = BookFormState(
            nameOfBook = book.title,
            author = book.author,
            year = book.year.toString(),
            description = book.description,
            rating = book.rating,
            status = book.status,
            startDate = book.startDate,
            endDate = book.endDate,
            nameError = "",
            authorError = "",
            yearError = "",
            dateReadingError = ""
        )
    }

    val isValid: Boolean
        get() = state.nameError.isEmpty()
            && state.authorError.isEmpty()
            && state.yearError.isEmpty()
                && state.dateReadingError.isEmpty()

    private fun validateDates (
        start: LocalDate?,
        end: LocalDate?
    ): String {
        if (start != null && end != null) {
            if (end.isBefore(start)) {
                return "End date can't be before start date"
            }
            if (start.isAfter(nowLocalDate)) {
                return "Start date can't be after now date"
            }
        }
        return ""
    }

}