package ru.kraz.randomfriend.domain.people

import ru.kraz.randomfriend.domain.common.ResultFDS

class FetchPeopleUseCase(
    private val repository: RandomPeopleRepository
) {
    suspend operator fun invoke(): ResultFDS<List<RandomPersonDomain>> =
        repository.fetchPeople()
}