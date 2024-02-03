package ru.kraz.randomfriend.presentation.chat

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.kraz.randomfriend.R

@Composable
fun Chat(
    navController: NavController, friendId: String?, modifier: Modifier = Modifier,
    chatViewModel: ChatViewModel = koinViewModel()
) {
    val messagesState by chatViewModel.uiState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val id = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
            .getString("UUID", "") ?: ""
        chatViewModel.fetchMessages(id, friendId ?: "")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            itemsIndexed(messagesState.messages) { index, item ->
                LaunchedEffect(Unit) {
                    if (!item.iSendThis && !item.messageRead) {
                        val id = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
                            .getString("UUID", "") ?: ""
                        chatViewModel.readMessage(index, id, friendId ?: "")
                    }
                }
                Message(item)
            }
        }
        SendContainer(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.BottomCenter),
            friendId ?: ""
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Message(item: MessageUi, modifier: Modifier = Modifier) {
    if (item.iSendThis) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(4.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Card(modifier = Modifier
                .fillMaxWidth(0.6f)
                .fillMaxHeight()
                .align(Alignment.TopEnd), onClick = {}) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(modifier = Modifier.wrapContentSize(), text = item.message)
                    Image(
                        modifier = Modifier
                            .width(15.dp)
                            .height(15.dp)
                            .align(Alignment.End),
                        painter = painterResource(id = if (item.messageRead) R.drawable.ic_done_all else R.drawable.ic_done),
                        contentDescription = null
                    )
                }
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(4.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Card(modifier = Modifier
                .fillMaxWidth(0.6f)
                .fillMaxHeight(), onClick = {}) {
                Text(modifier = Modifier.padding(4.dp), text = item.message)
            }
        }
    }
}

@Composable
fun SendContainer(
    modifier: Modifier = Modifier,
    friendId: String,
    chatViewModel: ChatViewModel = koinViewModel()
) {
    var textFieldValue by remember { mutableStateOf("") }

    val context = LocalContext.current

    Row(
        modifier = modifier
    ) {
        OutlinedTextField(modifier = Modifier.weight(1f),
            value = textFieldValue, onValueChange = {
                textFieldValue = it
            })
        Image(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .wrapContentHeight(Alignment.CenterVertically)
                .clickable {
                    if (textFieldValue.isNotEmpty()) {
                        val id = context
                            .getSharedPreferences("settings", Context.MODE_PRIVATE)
                            .getString("UUID", "") ?: ""
                        chatViewModel.sendMessage(textFieldValue, id, friendId)
                        textFieldValue = ""
                    }
                },
            contentDescription = null,
            imageVector = Icons.AutoMirrored.Filled.Send
        )
    }
}