package ru.kraz.randomfriend.di

import org.koin.dsl.module
import ru.kraz.randomfriend.domain.chat.FetchMessagesUseCase
import ru.kraz.randomfriend.domain.chat.ReadMessageUseCase
import ru.kraz.randomfriend.domain.chat.SendMessageUseCase
import ru.kraz.randomfriend.domain.common.AddOrRemoveFriendUseCase
import ru.kraz.randomfriend.domain.friends.FetchFriendsUseCase
import ru.kraz.randomfriend.domain.people.FetchPeopleUseCase

val useCasesModule = module {
    factory<FetchPeopleUseCase> {
        FetchPeopleUseCase(get())
    }

    factory<AddOrRemoveFriendUseCase> {
        AddOrRemoveFriendUseCase(get())
    }

    factory<FetchFriendsUseCase> {
        FetchFriendsUseCase(get())
    }

    factory<ReadMessageUseCase> {
        ReadMessageUseCase(get())
    }

    factory<SendMessageUseCase> {
        SendMessageUseCase(get())
    }

    factory<FetchMessagesUseCase> {
        FetchMessagesUseCase(get())
    }
}