package ru.kraz.randomfriend.domain.friends

import kotlinx.coroutines.flow.Flow
import ru.kraz.randomfriend.domain.people.RandomPersonDomain

interface FriendsRepository {
    suspend fun addOrRemove(friend: RandomPersonDomain, uuid: String, isFavorite: Boolean)
    suspend fun fetchFriends(uuid: String): Flow<List<FriendDomain>>
}