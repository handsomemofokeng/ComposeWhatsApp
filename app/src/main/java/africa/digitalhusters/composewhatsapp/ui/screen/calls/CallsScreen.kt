package africa.digitalhusters.composewhatsapp.ui.screen.calls

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CallsScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Calls Screen",
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}