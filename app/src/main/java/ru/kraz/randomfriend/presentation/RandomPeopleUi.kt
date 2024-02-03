package ru.kraz.randomfriend.presentation

data class RandomPeopleUi(
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
)

data class RandomPeopleUiState(
    val items: List<RandomPeopleUi> = emptyList(),
    val msg: Int? = null,
    val isLoading: Boolean = false
)