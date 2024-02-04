package ru.kraz.randomfriend.presentation.chat

import ru.kraz.randomfriend.domain.chat.MessageDomain
import ru.kraz.randomfriend.domain.common.ToMapper

class ToMessageUiMapper : ToMapper<MessageDomain, MessageUi> {
    override fun map(data: MessageDomain): MessageUi =
        MessageUi(
            id = data.id,
            message = data.message,
            senderId = data.senderId,
            createdDate = data.createdDate,
            createdDateMap = data.createdDateMap,
            iSendThis = data.iSendThis,
            messageRead = data.messageRead
        )
}