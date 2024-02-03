package ru.kraz.randomfriend.presentation

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
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun RandomPeople(
    navController: NavController, modifier: Modifier = Modifier,
    randomPeopleViewModel: RandomPeopleViewModel = koinViewModel()
) {
    val people by randomPeopleViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        randomPeopleViewModel.fetchPeople()
    }

    people.msg?.let {
        ErrorResult(message = it) {
            randomPeopleViewModel.fetchPeople()
        }
    }

    if (people.isLoading) Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) { CircularProgressIndicator() }

    LazyColumn {
        if (people.items.isNotEmpty()) {
            itemsIndexed(people.items) { index, it ->
                RandomPerson(it, index)
            }
        }
    }
}