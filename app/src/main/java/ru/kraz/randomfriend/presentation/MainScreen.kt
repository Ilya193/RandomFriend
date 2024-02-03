package ru.kraz.randomfriend.presentation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import ru.kraz.randomfriend.R

sealed class MainScreen(val route: String, val icon: ImageVector, @StringRes val resourceId: Int) {
    data object RandomPeople : MainScreen("RandomFriend", Icons.Filled.Person, R.string.people)
    data object FavoriteFriends : MainScreen("FavoriteFriends", Icons.Filled.Favorite, R.string.favorite)
}

sealed class Screen(val route: String) {
    data object Chat : Screen("Chat")
}