package ru.kraz.randomfriend.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatViewModel(
    private val database: FirebaseDatabase
) : ViewModel() {

    private var messages = mutableListOf<MessageUi>()
    private val _uiState = MutableStateFlow(MessageUiState(isLoading = true))
    val uiState: StateFlow<MessageUiState> get() = _uiState

    private val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())

    fun sendMessage(text: String, me: String, friend: String) = viewModelScope.launch(Dispatchers.IO) {
        val id = database.reference.child("chat/$me/messages/$friend").push().key
        database.reference.child("chat/$me/messages/$friend/$id").setValue(
            MessageCloud(
                id = id.toString(),
                message = text,
                senderId = me
            )
        )
    }

    fun readMessage(index: Int, me: String, friend: String) = viewModelScope.launch(Dispatchers.IO) {
        val message = messages[index].copy(messageRead = true)
        delay(2000)
        database.reference.child("chat/$me/messages/$friend/${message.id}")
            .setValue(message.map())
    }

    fun fetchMessages(me: String, friend: String) = viewModelScope.launch(Dispatchers.IO) {
        database.reference.child("chat/$me/messages/$friend")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    messages.clear()
                    for (item in snapshot.children) {
                        val messageCloud = item.getValue(MessageCloud::class.java)!!
                        val date = Date(messageCloud.createdDate["timestamp"] as Long)
                        val formattedDate = sdf.format(date)
                        if (messageCloud.senderId == me) messages.add(messageCloud.map(formattedDate, true))
                        else messages.add(messageCloud.map(formattedDate, false))
                    }
                    _uiState.value = MessageUiState(messages.toList(), messages.isEmpty(), null, false)
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }
}

data class MessageCloud(
    val id: String = "",
    val message: String = "",
    val senderId: String = "",
    val createdDate: Map<String, Any> = mapOf("timestamp" to ServerValue.TIMESTAMP),
    val messageRead: Boolean = false,
) {
    fun map(formattedDate: String, iSendThis: Boolean): MessageUi =
        MessageUi(
            id,
            message,
            senderId,
            formattedDate,
            createdDate,
            iSendThis,
            messageRead
        )
}

data class MessageUi(
    val id: String,
    val message: String,
    val senderId: String,
    val createdDate: String,
    val createdDateMap: Map<String, Any>,
    val iSendThis: Boolean = false,
    val messageRead: Boolean = false,
) {
    fun map(): MessageCloud = MessageCloud(
        id,
        message,
        senderId,
        createdDateMap,
        messageRead
    )
}

data class MessageUiState(
    val messages: List<MessageUi> = emptyList(),
    val isEmpty: Boolean = false,
    val msg: Int? = null,
    val isLoading: Boolean = false
)