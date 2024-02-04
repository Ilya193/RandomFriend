package ru.kraz.randomfriend.data.chat

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import ru.kraz.randomfriend.domain.chat.ChatRepository
import ru.kraz.randomfriend.domain.chat.MessageDomain
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatRepositoryImpl(
    private val database: FirebaseDatabase,
    private val mapper: ToMessageDataMapper
) : ChatRepository {

    private val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    override suspend fun sendMessage(text: String, me: String, friend: String) {
        val id = database.reference.child("chat/$me/messages/$friend").push().key
        database.reference.child("chat/$me/messages/$friend/$id").setValue(
            MessageCloud(
                id = id.toString(),
                message = text,
                senderId = me
            )
        )
    }

    override suspend fun readMessage(message: MessageDomain, me: String, friend: String) {
        val messageData = mapper.map(message.copy(messageRead = true))
        database.reference.child("chat/$me/messages/$friend/${message.id}")
            .setValue(messageData.toMessageCloud())
    }

    override suspend fun fetchMessages(me: String, friend: String): Flow<List<MessageDomain>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<MessageDomain>()
                for (item in snapshot.children) {
                    val messageCloud = item.getValue(MessageCloud::class.java)!!
                    val date = Date(messageCloud.createdDate["timestamp"] as Long)
                    val formattedDate = sdf.format(date)
                    if (messageCloud.senderId == me) list.add(messageCloud.toMessageData(formattedDate, true).toMessageDomain())
                    else list.add(messageCloud.toMessageData(formattedDate, false).toMessageDomain())
                }
                trySend(list)
            }

            override fun onCancelled(error: DatabaseError) {}
        }
        database.reference.child("chat/$me/messages/$friend")
            .addValueEventListener(listener)
        awaitClose { database.reference.removeEventListener(listener) }
    }
}