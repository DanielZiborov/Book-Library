package com.example.booklibrary.data.mappers

import com.example.booklibrary.domain.entities.Status

fun String.toStatusEntity() = when (this) {
    "не начата" -> Status.NOTREADING
    "в процессе" -> Status.INPROGRESS
    "прочитана" -> Status.READING
    else -> Status.NOTREADING
}

fun Status.toStatusModel() = when (this) {
    Status.NOTREADING -> "не начата"
    Status.INPROGRESS -> "в процессе"
    Status.READING -> "прочитана"
}