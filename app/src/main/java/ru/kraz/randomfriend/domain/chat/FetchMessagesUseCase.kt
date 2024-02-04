package ru.kraz.randomfriend.domain.chat

import kotlinx.coroutines.flow.Flow


class FetchMessagesUseCase(
    private val repository: ChatRepository
) {

    suspend operator fun invoke(me: String, friend: String): Flow<List<MessageDomain>> =
        repository.fetchMessages(me, friend)
}