package africa.digitalhusters.composewhatsapp.ui.screen.updates

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun UpdatesScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Updates Screen",
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}