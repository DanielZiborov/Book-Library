package com.example.booklibrary.data.models

import com.example.booklibrary.domain.entities.Status
import java.util.UUID

data class BookInfoModel(
    val id: UUID,
    val bookModel: BookModel,
    val rating: Int = 0,
    val startDate: String? = null,
    val endDate: String? = null,
    val status: Status = Status.NOTREADING
)
