package com.example.booklibrary.presentation.state

import com.example.booklibrary.domain.entities.BookEntity
import com.example.booklibrary.domain.entities.Statistic
import com.example.booklibrary.domain.entities.Status

data class BookUiState (
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = true,
    val currentSort: String? = null,
    val currentStatus: Status? = null,
    val books: List<BookEntity> = emptyList(),
    val statistic: Statistic = Statistic()
)