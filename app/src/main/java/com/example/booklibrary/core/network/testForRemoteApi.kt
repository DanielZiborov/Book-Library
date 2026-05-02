package com.example.booklibrary.core.network

import com.example.booklibrary.data.models.BookData
import com.example.booklibrary.data.models.BookModel

val data = BookData(
    books = listOf(
        BookModel(
            id = "b1a2c3d4-1111-4a11-9f2b-1c7e8d9a0b11",
            title = "TEST1",
            author = "ABC",
            year = 1967,
            description = "TEST1",
            readStatus = "не начата",
            updatedAt = "2026-04-05T10:00:00"
        ),
        BookModel(
            id = "b2b3c4d5-2222-4b22-8d3c-2f6d4b7e2c22",
            title = "TEST2",
            author = "abc",
            year = 1866,
            description = "TEST2",
            readStatus = "в процессе",
            rating = 4,
            startDate = "2026-04-01",
            updatedAt = "2026-04-05T10:05:00"
        ),
        BookModel(
            id = "b3c4d5e6-3333-4c33-9e44-3f7e1d2c3d33",
            title = "TEST3",
            author = "def",
            year = 1967,
            description = "TEST3",
            readStatus = "прочитана",
            updatedAt = "2026-04-05T10:00:00"
        ),
        BookModel(
            id = "b4d5e6f7-4444-4d44-8f55-4a1b2c3d4e44",
            title = "TEST4",
            author = "az",
            year = 1967,
            description = "TEST4",
            readStatus = "не начата",
            updatedAt = "2026-04-05T10:00:00"
        )
    )
)

val successResult: NetworkResult<BookData> = NetworkResult.Success(data = data)
val errorResult: NetworkResult<BookData> = NetworkResult.Error(null, message = "TEST ERROR")
