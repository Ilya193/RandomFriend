package ru.kraz.randomfriend.presentation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import ru.kraz.randomfriend.R

sealed class Screen(val route: String, val icon: ImageVector, @StringRes val resourceId: Int) {
    data object RandomPeople : Screen("RandomFriend", Icons.Filled.Person, R.string.people)
    data object FavoriteFriends : Screen("FavoriteFriends", Icons.Filled.Favorite,
        R.string.favorite
    )
}