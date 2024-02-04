package ru.kraz.randomfriend.di

import org.koin.dsl.module
import ru.kraz.randomfriend.data.chat.ChatRepositoryImpl
import ru.kraz.randomfriend.data.friends.FriendsRepositoryImpl
import ru.kraz.randomfriend.data.people.RandomPeopleRepositoryImpl
import ru.kraz.randomfriend.domain.chat.ChatRepository
import ru.kraz.randomfriend.domain.friends.FriendsRepository
import ru.kraz.randomfriend.domain.people.RandomPeopleRepository

val repositoryModule = module {
    single<RandomPeopleRepository> {
        RandomPeopleRepositoryImpl(get())
    }

    single<FriendsRepository> {
        FriendsRepositoryImpl(get(), get())
    }

    single<ChatRepository> {
        ChatRepositoryImpl(get(), get())
    }
}