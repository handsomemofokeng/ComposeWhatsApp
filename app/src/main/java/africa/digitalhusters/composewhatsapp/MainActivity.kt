package africa.digitalhusters.composewhatsapp

import africa.digitalhusters.composewhatsapp.ui.components.BottomNavigationBar
import africa.digitalhusters.composewhatsapp.ui.theme.ComposeWhatsAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeWhatsAppTheme {
                HomeScreenContent()
            }
        }
    }
}

@Composable
fun HomeScreenContent(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier,
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->
        NavigationHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MainScreenContentPreview() {
    ComposeWhatsAppTheme {
        HomeScreenContent()
    }
}