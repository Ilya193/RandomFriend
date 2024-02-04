package ru.kraz.randomfriend.domain.friends

import kotlinx.coroutines.flow.Flow
import ru.kraz.randomfriend.domain.people.RandomPersonDomain

class FetchFriendsUseCase(
    private val friendsRepository: FriendsRepository
) {

    suspend operator fun invoke(uuid: String): Flow<List<FriendDomain>> =
        friendsRepository.fetchFriends(uuid)
}