package ru.kraz.randomfriend.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.kraz.randomfriend.domain.chat.FetchMessagesUseCase
import ru.kraz.randomfriend.domain.chat.MessageDomain
import ru.kraz.randomfriend.domain.chat.ReadMessageUseCase
import ru.kraz.randomfriend.domain.chat.SendMessageUseCase
import java.text.SimpleDateFormat
import java.util.Locale

class ChatViewModel(
    private val mapper: ToMessageUiMapper,
    private val readMessageUseCase: ReadMessageUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val fetchMessagesUseCase: FetchMessagesUseCase
) : ViewModel() {

    private var messages = mutableListOf<MessageUi>()
    private val _uiState = MutableStateFlow(MessageUiState(isLoading = true))
    val uiState: StateFlow<MessageUiState> get() = _uiState

    private val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())

    fun sendMessage(text: String, me: String, friend: String) =
        viewModelScope.launch(Dispatchers.IO) {
            sendMessageUseCase(text, me, friend)
        }

    fun readMessage(index: Int, me: String, friend: String) =
        viewModelScope.launch(Dispatchers.IO) {
            readMessageUseCase(messages[index].toMessageDomain(), me, friend)
        }

    fun fetchMessages(me: String, friend: String) = viewModelScope.launch(Dispatchers.IO) {
        fetchMessagesUseCase(me, friend).collect { messagesDomain ->
            messages = messagesDomain.map { mapper.map(it) }.toMutableList()
            _uiState.value = MessageUiState(messages.toList(), messages.isEmpty(), null, false)
        }
    }
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

    fun toMessageDomain(): MessageDomain =
        MessageDomain(
            id,
            message,
            senderId,
            createdDate,
            createdDateMap,
            iSendThis,
            messageRead
        )
}

data class MessageUiState(
    val messages: List<MessageUi> = emptyList(),
    val isEmpty: Boolean = false,
    val msg: Int? = null,
    val isLoading: Boolean = false
)