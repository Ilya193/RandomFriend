package ru.kraz.randomfriend.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.kraz.randomfriend.presentation.RandomPeopleViewModel

val viewModelsModule = module {
    viewModel<RandomPeopleViewModel> {
        RandomPeopleViewModel(get(), get(), get(), get())
    }
}