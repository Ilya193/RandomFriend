package ru.kraz.randomfriend.domain.chat

import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun sendMessage(text: String, me: String, friend: String)
    suspend fun readMessage(message: MessageDomain, me: String, friend: String)
    suspend fun fetchMessages(me: String, friend: String): Flow<List<MessageDomain>>
}