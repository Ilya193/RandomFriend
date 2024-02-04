package ru.kraz.randomfriend.data.chat

import ru.kraz.randomfriend.domain.chat.MessageDomain

data class MessageData(
    val id: String,
    val message: String,
    val senderId: String,
    val createdDate: String,
    val createdDateMap: Map<String, Any>,
    val iSendThis: Boolean = false,
    val messageRead: Boolean = false,
) {
    fun toMessageCloud(): MessageCloud = MessageCloud(
        id,
        message,
        senderId,
        createdDateMap,
        messageRead
    )

    fun toMessageDomain(): MessageDomain = MessageDomain(
        id,
        message,
        senderId,
        createdDate,
        createdDateMap,
        iSendThis,
        messageRead
    )
}