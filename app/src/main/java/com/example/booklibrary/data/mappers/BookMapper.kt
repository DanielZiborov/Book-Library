package com.example.booklibrary.data.mappers

import com.example.booklibrary.data.models.BookModel
import com.example.booklibrary.domain.entities.BookEntity

fun BookModel.toEntity() = BookEntity(
    nameOfBook = nameOfBook,
    author = author,
    year = year,
    description = description
)

fun BookEntity.toModel() = BookModel(
    nameOfBook = nameOfBook,
    author = author,
    year = year,
    description = description
)
