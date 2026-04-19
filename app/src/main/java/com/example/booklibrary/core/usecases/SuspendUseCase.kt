package com.example.booklibrary.core.usecases

interface SuspendUseCase<Type, Params> {
    suspend operator fun invoke(params: Params): Type
}

suspend operator fun <Type> SuspendUseCase<Type, Unit>.invoke(): Type = invoke(Unit)