package ru.kraz.randomfriend.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.kraz.randomfriend.presentation.chat.Chat
import ru.kraz.randomfriend.presentation.friend.FavoriteFriends
import ru.kraz.randomfriend.presentation.people.RandomPeople
import ru.kraz.randomfriend.ui.theme.RandomFriendTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val id = sharedPreferences.getString("UUID", "")
        if (id == "" || id == null) sharedPreferences.edit().putString("UUID", UUID.randomUUID().toString()).apply()
        setContent {
            RandomFriendTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            BottomNavigation(
                                backgroundColor = Color.Blue
                            ) {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                screens.forEach { screen ->
                                    BottomNavigationItem(
                                        icon = { Icon(screen.icon, contentDescription = null) },
                                        label = {
                                            Text(
                                                text = stringResource(id = screen.resourceId),
                                                style = TextStyle(color = Color.White)
                                            )
                                        },
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        onClick = {
                                            if (currentDestination?.route != screen.route) {
                                                navController.popBackStack()
                                                navController.navigate(screen.route) {
                                                    popUpTo(navController.graph.findStartDestination().id) {
                                                        inclusive = true
                                                    }
                                                }
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController,
                            startDestination = MainScreen.RandomPeople.route,
                            Modifier.padding(innerPadding)
                        ) {
                            composable(MainScreen.RandomPeople.route) {
                                RandomPeople(navController)
                            }
                            composable(MainScreen.FavoriteFriends.route) {
                                FavoriteFriends(navController)
                            }
                            composable(Screen.Chat.route+"/{friendId}") {
                                Chat(navController, it.arguments?.getString("friendId"))
                            }
                        }
                    }
                }
            }
        }
    }

    private val screens = listOf(
        MainScreen.RandomPeople,
        MainScreen.FavoriteFriends,
    )
}