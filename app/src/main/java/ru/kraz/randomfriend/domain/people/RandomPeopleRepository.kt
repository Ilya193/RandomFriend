package ru.kraz.randomfriend.domain.people

import ru.kraz.randomfriend.domain.common.ResultFDS

interface RandomPeopleRepository {
    suspend fun fetchPeople(): ResultFDS<List<RandomPersonDomain>>
}