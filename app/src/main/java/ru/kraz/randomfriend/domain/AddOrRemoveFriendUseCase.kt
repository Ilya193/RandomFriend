package ru.kraz.randomfriend.domain

class AddOrRemoveFriendUseCase(
    private val repository: FriendsRepository
) {

    suspend operator fun invoke(friend: RandomPersonDomain, uuid: String, isFavorite: Boolean) =
        repository.addOrRemove(friend, uuid, isFavorite)
}