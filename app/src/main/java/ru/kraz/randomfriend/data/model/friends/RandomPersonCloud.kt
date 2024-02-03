package ru.kraz.randomfriend.data.model.friends

import ru.kraz.randomfriend.data.RandomPersonData

data class RandomPersonCloud(
    val id: String = "",
    val name: String = "",
    val phone: String = "",
    val country: String = "",
    val state: String = "",
    val city: String = "",
    val latitude: String = "",
    val longitude: String = "",
    val picture: String = "",
) {
    fun toRandomPersonData(): RandomPersonData =
        RandomPersonData(
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