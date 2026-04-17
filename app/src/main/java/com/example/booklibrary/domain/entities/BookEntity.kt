package com.example.booklibrary.domain.entities

import java.time.LocalDate
import java.util.UUID

data class BookEntity(
    val author: String,
    val description: String = "",
    val endDate: LocalDate? = null,
    val id: UUID,
    val rating: Int = 0,
    val status: Status = Status.NOTREADING,
    val startDate: LocalDate? = null,
    val title: String,
    val year: Int
)

enum class Status(val message: String) {
    NOTREADING("Not reading yet"),
    INPROGRESS("While reading"),
    READING("Already read")
}
