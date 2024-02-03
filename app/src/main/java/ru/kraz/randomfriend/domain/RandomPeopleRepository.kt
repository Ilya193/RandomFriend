package ru.kraz.randomfriend.domain

interface RandomPeopleRepository {
    suspend fun fetchPeople(): ResultFDS<List<RandomPersonDomain>>
}