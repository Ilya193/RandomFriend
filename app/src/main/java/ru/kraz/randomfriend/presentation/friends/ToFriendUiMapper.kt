package ru.kraz.randomfriend.presentation.friends

import ru.kraz.randomfriend.domain.people.RandomPersonDomain
import ru.kraz.randomfriend.domain.common.ToMapper
import ru.kraz.randomfriend.domain.friends.FriendDomain

class ToFriendUiMapper : ToMapper<List<FriendDomain>, List<FriendUi>> {
    override fun map(data: List<FriendDomain>): List<FriendUi> =
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