package ru.kraz.randomfriend.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.kraz.randomfriend.R

@Composable
fun ErrorResult(
    message: Int,
    modifier: Modifier = Modifier,
    btnRetry: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = message))
            Button(onClick = { btnRetry() }) {
                Text(text = stringResource(id = R.string.retry))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewErrorResult() {
    ErrorResult(message = R.string.no_connection) {}
}