package com.example.booklibrary.data.datasources.remote

import com.example.booklibrary.core.network.NetworkResult
import com.example.booklibrary.core.network.errorResult
import com.example.booklibrary.core.network.safeApiCall
import com.example.booklibrary.core.network.successResult
import com.example.booklibrary.data.datasources.remote.service.BooksApiService
import com.example.booklibrary.data.models.BookModel
import kotlinx.coroutines.delay
import javax.inject.Inject

class BooksRemoteDataSourceImpl @Inject constructor(
    private val booksApiService: BooksApiService
) : BooksRemoteDataSource {

    override suspend fun getBooksFromRemote(): NetworkResult<List<BookModel>> {
        delay(5000)

//        val result = safeApiCall {
//            booksApiService.getBooks()
//        }

        val result = successResult

//        val result = errorResult

        return when (result) {
            is NetworkResult.Success -> {
                NetworkResult.Success(result.data?.books ?: emptyList())
            }

            is NetworkResult.Error -> {
                NetworkResult.Error(null, result.message)
            }
        }
    }
}
