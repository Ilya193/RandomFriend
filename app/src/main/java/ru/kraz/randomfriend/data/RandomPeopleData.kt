package ru.kraz.randomfriend.data

import ru.kraz.randomfriend.domain.RandomPeopleDomain

data class RandomPeopleData(
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
    fun map(): RandomPeopleDomain =
        RandomPeopleDomain(
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