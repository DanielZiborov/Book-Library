package com.example.booklibrary.domain.entities

data class BookEntity(
    val nameOfBook: String,
    val author: String,
    val year: Int,
    val description: String
)