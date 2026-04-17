package com.example.booklibrary.data.datasources.remote

import com.example.booklibrary.core.network.NetworkResult
import com.example.booklibrary.data.models.BookModel

interface BooksRemoteDataSource {
    suspend fun getBooksFromRemote(): NetworkResult<List<BookModel>>
}
