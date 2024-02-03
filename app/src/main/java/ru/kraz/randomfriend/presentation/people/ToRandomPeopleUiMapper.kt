package ru.kraz.randomfriend.presentation.people

import ru.kraz.randomfriend.domain.RandomPersonDomain
import ru.kraz.randomfriend.domain.ToMapper

class ToRandomPeopleUiMapper : ToMapper<List<RandomPersonDomain>, List<RandomPersonUi>> {
    override fun map(data: List<RandomPersonDomain>): List<RandomPersonUi> =
        data.map {
            RandomPersonUi(
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