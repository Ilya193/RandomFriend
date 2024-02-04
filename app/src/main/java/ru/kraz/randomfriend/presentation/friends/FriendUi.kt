package ru.kraz.randomfriend.presentation.friends

import ru.kraz.randomfriend.domain.people.RandomPersonDomain

data class FriendUi(
    val id: String,
    val name: String,
    val phone: String,
    val country: String,
    val state: String,
    val city: String,
    val latitude: String,
    val longitude: String,
    val picture: String,
    val isFavorite: Boolean = false
) {
    fun map(): RandomPersonDomain =
        RandomPersonDomain(
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

data class FriendUiState(
    val friends: List<FriendUi> = emptyList(),
    val isEmpty: Boolean = false,
    val msg: Int? = null,
    val isLoading: Boolean = false
)