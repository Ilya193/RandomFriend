package ru.kraz.randomfriend.di

import org.koin.dsl.module
import ru.kraz.randomfriend.domain.AddOrRemoveFriendUseCase
import ru.kraz.randomfriend.domain.FetchPeopleUseCase

val useCasesModule = module {
    factory<FetchPeopleUseCase> {
        FetchPeopleUseCase(get())
    }

    factory<AddOrRemoveFriendUseCase> {
        AddOrRemoveFriendUseCase(get())
    }
}