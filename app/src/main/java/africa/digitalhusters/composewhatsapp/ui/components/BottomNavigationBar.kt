package africa.digitalhusters.composewhatsapp.ui.components

import africa.digitalhusters.composewhatsapp.R
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
) {
    val selectedIndex = rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(modifier = modifier) {
        bottomNavItems.forEachIndexed { index, bottomNavItem ->
            NavigationBarItem(
                selected = selectedIndex.intValue == index,
                label = { Text(text = bottomNavItem.label) },
                onClick = {
                    selectedIndex.intValue = index
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (bottomNavItem.hasUpdates) {
                                Badge()
                            } else if (bottomNavItem.badgeCount != null) {
                                Badge {
                                    Text(text = bottomNavItem.badgeCount.toString())
                                }
                            }
                        },
                        content = {
                            Icon(
                                painter = painterResource(
                                    id =
                                    if (selectedIndex.intValue == index) {
                                        bottomNavItem.selectedIcon
                                    } else {
                                        bottomNavItem.unselectedIcon
                                    }
                                ),
                                contentDescription = bottomNavItem.label
                            )
                        }
                    )

                }
            )
        }
    }
}

data class BottomNavItem(
    val label: String,
    val hasUpdates: Boolean,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val badgeCount: Int? = null,
)

val bottomNavItems = listOf(
    BottomNavItem(
        label = "Chats",
        selectedIcon = R.drawable.icn_message_filled,
        unselectedIcon = R.drawable.icn_message_outlined,
        badgeCount = 3,
        hasUpdates = false
    ),
    BottomNavItem(
        label = "Updates",
        selectedIcon = R.drawable.icn_updates_filled,
        unselectedIcon = R.drawable.icn_updates_outlined,
        hasUpdates = true
    ),
    BottomNavItem(
        label = "Communities",
        selectedIcon = R.drawable.icn_groups_filled,
        unselectedIcon = R.drawable.icn_groups_outlined,
        hasUpdates = false
    ),
    BottomNavItem(
        label = "Calls",
        selectedIcon = R.drawable.icn_phone_filled,
        unselectedIcon = R.drawable.icn_phone_outlined,
        hasUpdates = true
    )
)

@Preview(showSystemUi = true)
@Composable
private fun BottomNavItemNavBarPreview() {
    Column {
        Spacer(Modifier.weight(1f))
        BottomNavigationBar()
    }
}