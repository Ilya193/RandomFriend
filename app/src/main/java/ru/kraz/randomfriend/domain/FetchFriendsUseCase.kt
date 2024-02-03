package ru.kraz.randomfriend.domain

import kotlinx.coroutines.flow.Flow

class FetchFriendsUseCase(
    private val friendsRepository: FriendsRepository
) {

    suspend operator fun invoke(uuid: String): Flow<List<RandomPersonDomain>> =
        friendsRepository.fetchFriends(uuid)
}