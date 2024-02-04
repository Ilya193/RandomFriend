package ru.kraz.randomfriend.di

import org.koin.dsl.module
import ru.kraz.randomfriend.data.chat.ToMessageDataMapper
import ru.kraz.randomfriend.data.people.ToRandomPeopleDataMapper
import ru.kraz.randomfriend.presentation.chat.ToMessageUiMapper
import ru.kraz.randomfriend.presentation.friends.ToFriendUiMapper
import ru.kraz.randomfriend.presentation.people.ToRandomPeopleUiMapper

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

    factory<ToMessageDataMapper> {
        ToMessageDataMapper()
    }

    factory<ToMessageUiMapper> {
        ToMessageUiMapper()
    }
}