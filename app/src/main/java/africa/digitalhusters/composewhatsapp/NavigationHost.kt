package africa.digitalhusters.composewhatsapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.name,
        modifier = modifier
    ) {
        composable(route = Screen.Home.name) { }
    }
}

enum class Screen {
    Home
}