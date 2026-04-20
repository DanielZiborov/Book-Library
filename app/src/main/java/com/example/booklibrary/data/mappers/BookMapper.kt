package com.example.booklibrary.data.mappers

import com.example.booklibrary.data.models.BookModel
import com.example.booklibrary.domain.entities.BookEntity
import java.time.LocalDate
import java.util.UUID

fun BookModel.toEntity() = BookEntity(
    author = author,
    description = description,
    endDate = endDate?.let { LocalDate.parse(it) },
    id = UUID.fromString(id),
    rating = rating ?: 0,
    status = readStatus.toStatusEntity(),
    startDate = startDate?.let { LocalDate.parse(it) },
    title = title,
    year = year
)

fun BookEntity.toModel() = BookModel(
    author = author,
    description = description,
    endDate = endDate?.toString(),
    id = id.toString(),
    rating = rating,
    readStatus = status.toStatusModel(),
    startDate = startDate?.toString(),
    title = title,
    updatedAt = LocalDate.now().toString(),
    year = year
)
