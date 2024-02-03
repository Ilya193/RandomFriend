package ru.kraz.randomfriend.data

import ru.kraz.randomfriend.domain.RandomPersonDomain
import ru.kraz.randomfriend.domain.ToMapper

class ToRandomPeopleDataMapper : ToMapper<RandomPersonDomain, RandomPersonData> {
    override fun map(data: RandomPersonDomain): RandomPersonData =
        RandomPersonData(
            id = data.id,
            name = data.name,
            phone = data.phone,
            country = data.country,
            state = data.state,
            city = data.city,
            latitude = data.latitude,
            longitude = data.longitude,
            picture = data.picture
        )
}