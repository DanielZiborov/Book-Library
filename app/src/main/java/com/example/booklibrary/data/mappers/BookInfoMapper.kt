package com.example.booklibrary.data.mappers

import com.example.booklibrary.data.models.BookInfoModel
import com.example.booklibrary.domain.entities.BookInfoEntity
import java.time.LocalDate

fun BookInfoModel.toEntity() = BookInfoEntity(
    id = id,
    bookEntity = bookModel.toEntity(),
    rating = rating,
    startDate = startDate?.let { LocalDate.parse(it) },
    endDate = endDate?.let { LocalDate.parse(it) },
    status = status
)

fun BookInfoEntity.toModel() = BookInfoModel(
    id = id,
    bookModel = bookEntity.toModel(),
    rating = rating,
    startDate = startDate?.toString(),
    endDate = endDate?.toString(),
    status = status
)
