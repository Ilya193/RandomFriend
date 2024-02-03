package ru.kraz.randomfriend.presentation

import ru.kraz.randomfriend.domain.RandomPeopleDomain
import ru.kraz.randomfriend.domain.ToUiMapper

class ToRandomPeopleUiMapper : ToUiMapper<List<RandomPeopleDomain>, List<RandomPeopleUi>> {
    override fun map(data: List<RandomPeopleDomain>): List<RandomPeopleUi> =
        data.map {
            RandomPeopleUi(
                id = it.id,
                name = it.name,
                phone = it.phone,
                country = it.country,
                state = it.state,
                city = it.city,
                latitude = it.latitude,
                longitude = it.longitude,
                picture = it.picture
            )
        }
}