package ru.kraz.randomfriend.data.chat

import com.google.firebase.database.ServerValue

data class MessageCloud(
    val id: String = "",
    val message: String = "",
    val senderId: String = "",
    val createdDate: Map<String, Any> = mapOf("timestamp" to ServerValue.TIMESTAMP),
    val messageRead: Boolean = false,
) {
    fun toMessageData(formattedDate: String, iSendThis: Boolean): MessageData =
        MessageData(
            id,
            message,
            senderId,
            formattedDate,
            createdDate,
            iSendThis,
            messageRead
        )
}