package ru.kraz.randomfriend.domain

interface ToMapper<T, R> {
    fun map(data: T): R
}