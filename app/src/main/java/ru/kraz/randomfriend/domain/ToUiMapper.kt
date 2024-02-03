package ru.kraz.randomfriend.domain

interface ToUiMapper<T, R> {
    fun map(data: T): R
}