package com.example.booklibrary.domain.usecases

import com.example.booklibrary.core.usecases.UseCase
import com.example.booklibrary.domain.BookRepository
import com.example.booklibrary.domain.entities.BookEntity
import com.example.booklibrary.domain.entities.Status
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
     private val bookRepository: BookRepository
): UseCase<Flow<List<BookEntity>>, Parameters> {

     override fun invoke(params: Parameters): Flow<List<BookEntity>> {
          return bookRepository.getAllBooks(params.status, params.sortType)
     }
}

data class Parameters(
     val status: Status? = null,
     val sortType: String? = null
)
