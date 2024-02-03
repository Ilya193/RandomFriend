package ru.kraz.randomfriend.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.kraz.randomfriend.domain.FetchPeopleUseCase
import ru.kraz.randomfriend.domain.ResourceProvider
import ru.kraz.randomfriend.domain.ResultFDS

class RandomPeopleViewModel(
    private val fetchPeopleUseCase: FetchPeopleUseCase,
    private val mapper: ToRandomPeopleUiMapper,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private var people = mutableListOf<RandomPersonUi>()
    private val _uiState = MutableStateFlow(RandomPeopleUiState(isLoading = true))
    val uiState: StateFlow<RandomPeopleUiState> get() = _uiState

    fun fetchPeople() = viewModelScope.launch(Dispatchers.IO) {
        _uiState.value = RandomPeopleUiState(items = emptyList(), msg = null, isLoading = true)
        when (val result = fetchPeopleUseCase()) {
            is ResultFDS.Success -> {
                people = mapper.map(result.data).toMutableList()
                _uiState.value = RandomPeopleUiState(items = people.toList(), msg = null, isLoading = false)
            }

            is ResultFDS.Error -> {
                _uiState.value = RandomPeopleUiState(items = emptyList(), msg = resourceProvider.getString(result.e), isLoading = false)
            }
        }
    }

    fun addAsFriend(position: Int) {
        people[position] = people[position].copy(isFavorite = !people[position].isFavorite)
        _uiState.value = RandomPeopleUiState(items = people.toList(), msg = null, isLoading = false)
    }
}

