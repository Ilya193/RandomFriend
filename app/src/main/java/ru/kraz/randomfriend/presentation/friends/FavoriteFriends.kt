package ru.kraz.randomfriend.presentation.friends

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.kraz.randomfriend.presentation.common.ErrorResult

@Composable
fun FavoriteFriends(
    navController: NavController, modifier: Modifier = Modifier,
    friendsViewModel: FriendsViewModel = koinViewModel()
) {
    val friendsState by friendsViewModel.uiState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val id = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
            .getString("UUID", "") ?: ""
        friendsViewModel.fetchFriends(id)
    }

    friendsState.msg?.let {
        ErrorResult(message = it) {
            val id = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
                .getString("UUID", "") ?: ""
            friendsViewModel.fetchFriends(id)
        }
    }

    if (friendsState.isEmpty) NoFriendsAdded()

    if (friendsState.isLoading) Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) { CircularProgressIndicator() }

    LazyColumn {
        if (friendsState.friends.isNotEmpty()) {
            itemsIndexed(friendsState.friends) { index, it ->
                Friend(navController, it, index)
            }
        }
    }
}