package ru.kraz.randomfriend.presentation.people

import ru.kraz.randomfriend.domain.people.RandomPersonDomain
import ru.kraz.randomfriend.domain.common.ToMapper

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