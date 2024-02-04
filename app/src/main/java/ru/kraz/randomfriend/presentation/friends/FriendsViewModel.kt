package ru.kraz.randomfriend.presentation.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.kraz.randomfriend.domain.common.AddOrRemoveFriendUseCase
import ru.kraz.randomfriend.domain.friends.FetchFriendsUseCase

class FriendsViewModel(
    private val fetchFriendsUseCase: FetchFriendsUseCase,
    private val mapper: ToFriendUiMapper,
    private val addOrRemoveFriendUseCase: AddOrRemoveFriendUseCase
) : ViewModel() {

    private var friends = mutableListOf<FriendUi>()
    private val _uiState = MutableStateFlow(FriendUiState(isLoading = true))
    val uiState: StateFlow<FriendUiState> get() = _uiState

    fun fetchFriends(uuid: String) = viewModelScope.launch(Dispatchers.IO) {
        fetchFriendsUseCase(uuid).collect {
            friends = mapper.map(it).toMutableList()
            _uiState.value = FriendUiState(friends = friends.toList(), isEmpty = friends.isEmpty(), msg = null, isLoading = false)
        }
    }

    fun addAsFriend(position: Int, uuid: String) = viewModelScope.launch(Dispatchers.IO) {
        friends[position] = friends[position].copy(isFavorite = !friends[position].isFavorite)
        addOrRemoveFriendUseCase(friends[position].map(), uuid, friends[position].isFavorite)
        _uiState.value = FriendUiState(friends = friends.toList(), isEmpty = friends.isEmpty(), msg = null, isLoading = false)
    }
}