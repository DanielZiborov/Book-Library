package com.example.booklibrary.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "books_table")
@Serializable
data class BookModel(
    @SerialName("author")
    val author: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("endDate")
    val endDate: String? = null,
    @PrimaryKey
    @SerialName("id")
    val id: String,
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