package africa.digitalhusters.composewhatsapp.ui.screen.home

import africa.digitalhusters.composewhatsapp.NavigationHost
import africa.digitalhusters.composewhatsapp.R
import africa.digitalhusters.composewhatsapp.ui.components.BottomNavigationBar
import africa.digitalhusters.composewhatsapp.ui.components.ComposeWhatsAppTopAppBar
import africa.digitalhusters.composewhatsapp.ui.theme.ComposeWhatsAppTheme
import africa.digitalhusters.composewhatsapp.ui.theme.Dimensions
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    HomeScreenContent(modifier = modifier)
}

@Composable
fun HomeScreenContent(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val context = LocalContext.current
    Scaffold(
        modifier = modifier,
        topBar = {
            ComposeWhatsAppTopAppBar(
                title = "Compose WhatsApp",
                isBold = true,
                actionIcons = {
                    Icon(
                        painter = painterResource(R.drawable.icn_camera),
                        contentDescription = stringResource(R.string.camera_icon_content_description),
                        modifier = Modifier
                            .size(Dimensions.XLarge)
                            .clickable {
                                Toast.makeText(context, "Camera icon", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    )

                    Spacer(Modifier.width(Dimensions.Large))

                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(R.string.more_menu_items_content_description),
                        modifier = Modifier
                            .size(Dimensions.XLarge)
                            .clickable {
                                Toast.makeText(context, "More menu icon", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    )

                }
            )
        },
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