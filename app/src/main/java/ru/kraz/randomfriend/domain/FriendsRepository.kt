package ru.kraz.randomfriend.domain

import kotlinx.coroutines.flow.Flow

interface FriendsRepository {
    suspend fun addOrRemove(friend: RandomPersonDomain, uuid: String, isFavorite: Boolean)
    suspend fun fetchFriends(uuid: String): Flow<List<RandomPersonDomain>>
}