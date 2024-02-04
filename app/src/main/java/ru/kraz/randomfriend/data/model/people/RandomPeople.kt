package ru.kraz.randomfriend.data.model.people

import ru.kraz.randomfriend.data.people.RandomPersonData

data class RandomPeople(
    val info: Info,
    val results: List<Result>
) {
    fun map(): List<RandomPersonData> = results.map {
        RandomPersonData(
            id = it.login.uuid,
            name = it.name.title + " " + it.name.first + " " + it.name.last,
            phone = it.phone,
            country = it.location.country,
            state = it.location.state,
            city = it.location.city,
            latitude = it.location.coordinates.latitude,
            longitude = it.location.coordinates.longitude,
            picture = it.picture.large
        )
    }
}