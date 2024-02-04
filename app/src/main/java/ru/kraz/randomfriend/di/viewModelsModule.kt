package ru.kraz.randomfriend.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.kraz.randomfriend.presentation.chat.ChatViewModel
import ru.kraz.randomfriend.presentation.friends.FriendsViewModel
import ru.kraz.randomfriend.presentation.people.RandomPeopleViewModel

val viewModelsModule = module {
    viewModel<RandomPeopleViewModel> {
        RandomPeopleViewModel(get(), get(), get(), get())
    }

    viewModel<FriendsViewModel> {
        FriendsViewModel(get(), get(), get())
    }

    viewModel<ChatViewModel> {
        ChatViewModel(get(), get(), get(), get())
    }
}