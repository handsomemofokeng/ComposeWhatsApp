package africa.digitalhusters.composewhatsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Chats.name
    ) {
        composable(route = Screen.Chats.name) {  }
        composable(route = Screen.Updates.name) {  }
        composable(route = Screen.Communities.name) {  }
        composable(route = Screen.Calls.name) {  }
    }
}

enum class Screen{
    Chats,
    Updates,
    Communities,
    Calls
}