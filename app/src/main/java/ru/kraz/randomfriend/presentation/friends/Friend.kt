package ru.kraz.randomfriend.presentation.friends

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import ru.kraz.randomfriend.R
import ru.kraz.randomfriend.presentation.common.Screen
import ru.kraz.randomfriend.presentation.people.TextLocation

@Composable
fun Friend(
    navController: NavController,
    it: FriendUi,
    index: Int,
    friendsViewModel: FriendsViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .padding(4.dp),
        border = BorderStroke(width = 1.dp, color = Color.Black),
        colors = CardDefaults.elevatedCardColors()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
            ) {
                var isLoadingImage by remember { mutableStateOf(true) }
                var isError by remember { mutableStateOf(false) }
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(CircleShape),
                    model = if (isError) painterResource(id = R.drawable.ic_error) else it.picture,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    onLoading = { isLoadingImage = true },
                    onSuccess = { isLoadingImage = false },
                    onError = {
                        isLoadingImage = false
                        isError = true
                    }
                )
                if (isLoadingImage) CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .align(Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = it.name,
                    style = TextStyle(
                        fontSize = 20.sp, fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
                Row {
                    Image(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(horizontal = 10.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) { navController.navigate(Screen.Chat.route+"/${it.id}") },
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(horizontal = 10.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                val sharedPreferences =
                                    context.getSharedPreferences("settings", Context.MODE_PRIVATE)
                                val uuid = sharedPreferences.getString("UUID", "") ?: ""
                                friendsViewModel.addAsFriend(index, uuid)
                            },
                        imageVector = if (it.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = null
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 15.dp),
        ) {
            Text(
                text = stringResource(R.string.phone),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp), text = it.phone
            )
            Text(
                text = stringResource(R.string.location),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
            )
            TextLocation(text = it.country)
            TextLocation(text = it.state)
            TextLocation(text = it.city)
            Text(
                text = it.latitude + " " + it.longitude,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 2.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("geo:${it.latitude + "," + it.longitude}")
                            )
                        )
                    }, style = TextStyle(color = Color.Blue)
            )
        }
    }
}