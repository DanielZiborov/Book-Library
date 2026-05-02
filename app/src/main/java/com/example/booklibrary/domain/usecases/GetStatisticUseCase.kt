package com.example.booklibrary.domain.usecases

import com.example.booklibrary.core.usecases.UseCase
import com.example.booklibrary.domain.BookRepository
import com.example.booklibrary.domain.entities.Statistic
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStatisticUseCase @Inject constructor(
    private val bookRepository: BookRepository
) : UseCase<Flow<Statistic>, Unit> {

    override fun invoke(params: Unit): Flow<Statistic> {
        return bookRepository.getStatistic()
    }

}