package com.example.booklibrary.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookData(
    @SerialName("books")
    val books: List<BookModel> = listOf()
)