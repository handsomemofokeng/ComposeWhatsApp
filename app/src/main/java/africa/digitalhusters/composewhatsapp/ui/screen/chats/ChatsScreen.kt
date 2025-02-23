package africa.digitalhusters.composewhatsapp.ui.screen.chats

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ChatsScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Chat Screen",
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}