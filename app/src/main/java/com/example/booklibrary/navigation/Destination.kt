package com.example.booklibrary.navigation

import java.util.UUID

sealed class Destination(val route: String) {
    data object BookList : Destination(BOOK_LIST)
    data object Details : Destination(DETAILS) {
        fun createRoute(bookId: UUID) = "details/$bookId"
    }
    data object Add : Destination(ADD)
    companion object {
        const val BOOK_LIST = "booklist"
        const val ADD = "add"
        const val DETAILS = "details/{bookId}"
        const val BOOK_ID = "bookId"
    }
}
