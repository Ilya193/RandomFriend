package ru.kraz.randomfriend.domain.chat

class SendMessageUseCase(
    private val repository: ChatRepository
) {

    suspend operator fun invoke(text: String, me: String, friend: String) =
        repository.sendMessage(text, me, friend)
}