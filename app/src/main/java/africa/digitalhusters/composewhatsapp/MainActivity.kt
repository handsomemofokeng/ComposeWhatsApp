package africa.digitalhusters.composewhatsapp

import africa.digitalhusters.composewhatsapp.ui.theme.ComposeWhatsAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeWhatsAppTheme {
            }
        }
    }
}