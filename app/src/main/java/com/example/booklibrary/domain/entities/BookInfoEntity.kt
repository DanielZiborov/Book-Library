package com.example.booklibrary.domain.entities

import java.time.LocalDate
import java.util.UUID

data class BookInfoEntity(
    val id: UUID,
    val bookEntity: BookEntity,
    val rating: Int = 0,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val status: Status = Status.NOTREADING
)

enum class Status(val message: String) {
    NOTREADING("Not reading yet"),
    INPROGRESS("While reading"),
    READING("Already read")
}