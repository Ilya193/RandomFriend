package ru.kraz.randomfriend.data.chat

import ru.kraz.randomfriend.domain.chat.MessageDomain
import ru.kraz.randomfriend.domain.common.ToMapper

class ToMessageDataMapper : ToMapper<MessageDomain, MessageData> {
    override fun map(data: MessageDomain): MessageData =
        MessageData(
            id = data.id,
            message = data.message,
            senderId = data.senderId,
            createdDate = data.createdDate,
            createdDateMap = data.createdDateMap,
            iSendThis = data.iSendThis,
            messageRead = data.messageRead
        )
}