package ru.kraz.randomfriend.data.model

import ru.kraz.randomfriend.data.RandomPeopleData

data class RandomPeople(
    val info: Info,
    val results: List<Result>
) {
    fun map(): List<RandomPeopleData> = results.map {
        RandomPeopleData(
            id = it.login.uuid,
            name = it.name.title + " " + it.name.first + " " + it.name.last,
            phone = it.phone,
            country = it.location.country,
            state = it.location.state,
            city = it.location.state,
            latitude = it.location.coordinates.latitude,
            longitude = it.location.coordinates.longitude,
            picture = it.picture.large
        )
    }
}