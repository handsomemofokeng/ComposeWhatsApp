package africa.digitalhusters.composewhatsapp.ui.screen.home

import africa.digitalhusters.composewhatsapp.R
import africa.digitalhusters.composewhatsapp.ui.screen.calls.CallsScreen
import africa.digitalhusters.composewhatsapp.ui.screen.chat_list.ChatListScreen
import africa.digitalhusters.composewhatsapp.ui.screen.communities.CommunitiesScreen
import africa.digitalhusters.composewhatsapp.ui.screen.home.components.BottomNavigationBar
import africa.digitalhusters.composewhatsapp.ui.screen.home.components.CallsScreenTopBar
import africa.digitalhusters.composewhatsapp.ui.screen.home.components.CommunitiesScreenTopBar
import africa.digitalhusters.composewhatsapp.ui.screen.home.components.HomeScreenTopBar
import africa.digitalhusters.composewhatsapp.ui.screen.home.components.UpdatesScreenTopBar
import africa.digitalhusters.composewhatsapp.ui.screen.home.components.getBottomNavigationItemList
import africa.digitalhusters.composewhatsapp.ui.screen.updates_list.UpdatesListScreen
import africa.digitalhusters.composewhatsapp.ui.theme.ComposeWhatsAppTheme
import africa.digitalhusters.composewhatsapp.ui.theme.DarkGrey
import africa.digitalhusters.composewhatsapp.ui.theme.Green
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    HomeScreenContent(modifier = modifier)
}

@Composable
fun HomeScreenContent(modifier: Modifier = Modifier) {
    val selectedScreenIndex = rememberSaveable { mutableIntStateOf(1) }
    val pagerState = rememberPagerState(pageCount = { getBottomNavigationItemList().size })
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            selectedScreenIndex.intValue = page
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            when (selectedScreenIndex.intValue) {
                1 -> UpdatesScreenTopBar(
                    onSearchClick = {},
                    onMenuClick = {}
                )

                2 -> CommunitiesScreenTopBar(
                    onMenuClick = {}
                )

                3 -> CallsScreenTopBar(
                    onSearchClick = {},
                    onMenuClick = {}
                )

                else -> HomeScreenTopBar(
                    onCameraClick = {},
                    onMenuClick = {}
                )
            }
        },
        content = { contentPadding ->
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.padding(contentPadding),
                pageContent = {
                    when (selectedScreenIndex.intValue) {
                        1 -> UpdatesListScreen()
                        2 -> CommunitiesScreen()
                        3 -> CallsScreen()
                        else -> ChatListScreen(
                            onSearchBarClick = {}
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Green,
                contentColor = DarkGrey,
                onClick = {},
                content = {
                    Icon(
                        painter = painterResource(R.drawable.icn_add_message),
                        contentDescription = stringResource(R.string.add_new_chat_content_description)
                    )
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                selectedIndex = selectedScreenIndex.intValue,
                onNavigationItemChanged = { index ->
                    selectedScreenIndex.intValue = index
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(selectedScreenIndex.intValue)
                    }
                }
            )
        }
    )
}

@Preview(showSystemUi = true)
@Composable
private fun MainScreenContentPreview() {
    ComposeWhatsAppTheme {
        HomeScreenContent()
    }
}