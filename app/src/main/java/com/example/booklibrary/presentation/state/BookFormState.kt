package com.example.booklibrary.presentation.state

import com.example.booklibrary.domain.entities.Status
import java.time.LocalDate

data class BookFormState(
    val nameOfBook: String = "",
    val author: String = "",
    val year: String = "",
    val description: String = "",
    val rating: Int = 0,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val status: Status = Status.NOTREADING,

    val nameError: String = "Empty name",
    val authorError: String = "Empty author",
    val yearError: String = "Invalid year",

    val dateReadingError: String = ""
)
