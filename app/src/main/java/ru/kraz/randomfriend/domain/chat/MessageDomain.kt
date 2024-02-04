package ru.kraz.randomfriend.domain.chat

data class MessageDomain(
    val id: String,
    val message: String,
    val senderId: String,
    val createdDate: String,
    val createdDateMap: Map<String, Any>,
    val iSendThis: Boolean = false,
    val messageRead: Boolean = false,
)