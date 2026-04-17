package com.example.booklibrary.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookModel(
    @SerialName("author")
    val author: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("endDate")
    val endDate: String? = null,
    @SerialName("id")
    val id: String = "",
    @SerialName("rating")
    val rating: Int? = null,
    @SerialName("readStatus")
    val readStatus: String = "",
    @SerialName("startDate")
    val startDate: String? = null,
    @SerialName("title")
    val title: String = "",
    @SerialName("updatedAt")
    val updatedAt: String = "",
    @SerialName("year")
    val year: Int = 0
)