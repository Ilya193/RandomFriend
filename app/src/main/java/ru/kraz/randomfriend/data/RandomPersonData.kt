package ru.kraz.randomfriend.data

import ru.kraz.randomfriend.data.model.friends.RandomPersonCloud
import ru.kraz.randomfriend.domain.RandomPersonDomain

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

    fun toRandomPersonCloud(): RandomPersonCloud =
        RandomPersonCloud(
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