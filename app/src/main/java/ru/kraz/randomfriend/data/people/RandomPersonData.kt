package ru.kraz.randomfriend.data.people

import ru.kraz.randomfriend.data.model.friends.FriendCloud
import ru.kraz.randomfriend.domain.people.RandomPersonDomain

data class RandomPersonData(
    val id: String,
    val name: String,
    val phone: String,
    val country: String,
    val state: String,
    val city: String,
    val latitude: String,
    val longitude: String,
    val picture: String,
) {
    fun toRandomPersonDomain(): RandomPersonDomain =
        RandomPersonDomain(
            id = id,
            name = name,
            phone = phone,
            country = country,
            state = state,
            city = city,
            latitude = latitude,
            longitude = longitude,
            picture = picture
        )

    fun toFriendCloud(): FriendCloud =
        FriendCloud(
            id = id,
            name = name,
            phone = phone,
            country = country,
            state = state,
            city = city,
            latitude = latitude,
            longitude = longitude,
            picture = picture
        )
}