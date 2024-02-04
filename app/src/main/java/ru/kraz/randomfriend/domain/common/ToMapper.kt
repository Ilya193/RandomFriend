package ru.kraz.randomfriend.domain.common

interface ToMapper<T, R> {
    fun map(data: T): R
}