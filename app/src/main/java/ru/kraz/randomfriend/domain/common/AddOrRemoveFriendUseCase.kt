package ru.kraz.randomfriend.domain.common

import ru.kraz.randomfriend.domain.friends.FriendsRepository
import ru.kraz.randomfriend.domain.people.RandomPersonDomain

class AddOrRemoveFriendUseCase(
    private val repository: FriendsRepository
) {

    suspend operator fun invoke(friend: RandomPersonDomain, uuid: String, isFavorite: Boolean) =
        repository.addOrRemove(friend, uuid, isFavorite)
}