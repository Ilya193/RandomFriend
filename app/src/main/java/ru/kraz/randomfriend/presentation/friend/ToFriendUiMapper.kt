package ru.kraz.randomfriend.presentation.friend

import ru.kraz.randomfriend.domain.RandomPersonDomain
import ru.kraz.randomfriend.domain.ToMapper

class ToFriendUiMapper : ToMapper<List<RandomPersonDomain>, List<FriendUi>> {
    override fun map(data: List<RandomPersonDomain>): List<FriendUi> =
        data.map {
            FriendUi(
                id = it.id,
                name = it.name,
                phone = it.phone,
                country = it.country,
                state = it.state,
                city = it.city,
                latitude = it.latitude,
                longitude = it.longitude,
                picture = it.picture,
                isFavorite = true
            )
        }
}