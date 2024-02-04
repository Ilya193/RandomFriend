package ru.kraz.randomfriend.domain.chat

class ReadMessageUseCase(
    private val repository: ChatRepository
) {

    suspend operator fun invoke(message: MessageDomain, me: String, friend: String) =
        repository.readMessage(message, me, friend)
}