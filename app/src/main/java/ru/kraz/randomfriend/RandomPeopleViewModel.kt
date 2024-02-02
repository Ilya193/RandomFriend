package ru.kraz.randomfriend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RandomPeopleViewModel(
    private val service: PeopleService
) : ViewModel() {

    private var people = mutableListOf<RandomPeopleUi>()
    private val _uiState = MutableStateFlow<List<RandomPeopleUi>>(emptyList())
    val uiState: StateFlow<List<RandomPeopleUi>> get() = _uiState

    fun fetchPeople() = viewModelScope.launch(Dispatchers.IO) {
        val result = service.fetchRandomPeople()
        people = result.results.map {
            RandomPeopleUi(
                id = it.login.uuid,
                name = it.name.title + " " + it.name.first + " " + it.name.last,
                phone = it.phone,
                country = it.location.country,
                state = it.location.state,
                city = it.location.state,
                latitude = it.location.coordinates.latitude,
                longitude = it.location.coordinates.longitude,
                picture = it.picture.large
            )
        }.toMutableList()
        _uiState.value = people.toList()
    }

    fun addAsFriend(position: Int) {
        people[position] = people[position].copy(isFavorite = !people[position].isFavorite)
        _uiState.value = people.toList()
    }
}

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