package ru.kraz.randomfriend.di

import org.koin.dsl.module
import ru.kraz.randomfriend.data.FriendsRepositoryImpl
import ru.kraz.randomfriend.data.RandomPeopleRepositoryImpl
import ru.kraz.randomfriend.domain.FriendsRepository
import ru.kraz.randomfriend.domain.RandomPeopleRepository

val repositoryModule = module {
    single<RandomPeopleRepository> {
        RandomPeopleRepositoryImpl(get())
    }

    single<FriendsRepository> {
        FriendsRepositoryImpl(get(), get())
    }
}