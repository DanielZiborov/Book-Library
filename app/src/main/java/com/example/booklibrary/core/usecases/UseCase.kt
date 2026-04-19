package com.example.booklibrary.core.usecases

interface UseCase<Type, Params> {
    operator fun invoke(params: Params): Type
}

operator fun <Type> UseCase<Type, Unit>.invoke(): Type = invoke(Unit)