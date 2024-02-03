package ru.kraz.randomfriend.domain

class FetchPeopleUseCase(
    private val repository: RandomPeopleRepository
) {
    suspend operator fun invoke(): ResultFDS<List<RandomPersonDomain>> =
        repository.fetchPeople()
}