package ru.kraz.randomfriend.data.friends

import ru.kraz.randomfriend.domain.friends.FriendDomain

data class FriendData(
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
    fun toFriendDomain(): FriendDomain =
        FriendDomain(
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