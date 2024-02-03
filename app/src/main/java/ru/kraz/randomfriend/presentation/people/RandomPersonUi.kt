package ru.kraz.randomfriend.presentation.people

import ru.kraz.randomfriend.domain.RandomPersonDomain

data class RandomPersonUi(
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

data class RandomPeopleUiState(
    val items: List<RandomPersonUi> = emptyList(),
    val msg: Int? = null,
    val isLoading: Boolean = false
)