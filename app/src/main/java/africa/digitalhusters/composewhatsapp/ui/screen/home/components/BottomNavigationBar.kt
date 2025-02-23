package africa.digitalhusters.composewhatsapp.ui.screen.home.components

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

const val CHATS = "Chats"
const val UPDATES = "Updates"
const val COMMUNITIES = "Communities"
const val CALLS = "Calls"

@Composable
fun BottomNavigationBar(
    selectedIndex: Int,
    onNavigationItemChanged: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val bottomNavItems = getBottomNavigationItemList()
    NavigationBar(modifier = modifier) {
        bottomNavItems.forEachIndexed { index, bottomNavItem ->
            NavigationBarItem(
                selected = selectedIndex == index,
                label = { Text(text = bottomNavItem.label) },
                onClick = {
                    onNavigationItemChanged(index)
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
                                    if (selectedIndex == index) {
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

fun getBottomNavigationItemList(): List<BottomNavItem> {
    val bottomNavItems = listOf(
        BottomNavItem(
            label = CHATS,
            selectedIcon = R.drawable.icn_message_filled,
            unselectedIcon = R.drawable.icn_message_outlined,
            badgeCount = 3,
            hasUpdates = false
        ),
        BottomNavItem(
            label = UPDATES,
            selectedIcon = R.drawable.icn_updates_filled,
            unselectedIcon = R.drawable.icn_updates_outlined,
            hasUpdates = true
        ),
        BottomNavItem(
            label = COMMUNITIES,
            selectedIcon = R.drawable.icn_groups_filled,
            unselectedIcon = R.drawable.icn_groups_outlined,
            hasUpdates = false
        ),
        BottomNavItem(
            label = CALLS,
            selectedIcon = R.drawable.icn_phone_filled,
            unselectedIcon = R.drawable.icn_phone_outlined,
            hasUpdates = true
        )
    )
    return bottomNavItems
}

data class BottomNavItem(
    val label: String,
    val hasUpdates: Boolean,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val badgeCount: Int? = null,
)

@Preview(showSystemUi = true)
@Composable
private fun BottomNavItemNavBarPreview() {
    Column {
        Spacer(Modifier.weight(1f))
        BottomNavigationBar(
            selectedIndex = 2,
            onNavigationItemChanged = {})
    }
}