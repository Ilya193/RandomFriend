package ru.kraz.randomfriend.presentation.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.kraz.randomfriend.domain.common.AddOrRemoveFriendUseCase
import ru.kraz.randomfriend.domain.people.FetchPeopleUseCase
import ru.kraz.randomfriend.domain.common.ResourceProvider
import ru.kraz.randomfriend.domain.common.ResultFDS

class RandomPeopleViewModel(
    private val fetchPeopleUseCase: FetchPeopleUseCase,
    private val mapper: ToRandomPeopleUiMapper,
    private val resourceProvider: ResourceProvider,
    private val addOrRemoveFriendUseCase: AddOrRemoveFriendUseCase
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

    fun addAsFriend(position: Int, uuid: String, strictlyAdd: Boolean = false) = viewModelScope.launch(Dispatchers.IO) {
        if (strictlyAdd)
            people[position] = people[position].copy(isFavorite = true)
        else
            people[position] = people[position].copy(isFavorite = !people[position].isFavorite)
        addOrRemoveFriendUseCase(people[position].map(), uuid, people[position].isFavorite)
        _uiState.value = RandomPeopleUiState(items = people.toList(), msg = null, isLoading = false)
    }
}

