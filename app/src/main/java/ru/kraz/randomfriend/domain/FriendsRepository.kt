package ru.kraz.randomfriend.domain

interface FriendsRepository {
    suspend fun addOrRemove(friend: RandomPersonDomain, uuid: String, isFavorite: Boolean)
}