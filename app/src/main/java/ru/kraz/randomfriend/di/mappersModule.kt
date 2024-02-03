package ru.kraz.randomfriend.di

import org.koin.dsl.module
import ru.kraz.randomfriend.data.ToRandomPeopleDataMapper
import ru.kraz.randomfriend.presentation.ToFriendUiMapper
import ru.kraz.randomfriend.presentation.ToRandomPeopleUiMapper

val mappersModule = module {
    factory<ToRandomPeopleUiMapper> {
        ToRandomPeopleUiMapper()
    }

    factory<ToRandomPeopleDataMapper> {
        ToRandomPeopleDataMapper()
    }

    factory<ToFriendUiMapper> {
        ToFriendUiMapper()
    }
}