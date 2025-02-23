package africa.digitalhusters.composewhatsapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Person
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
) {
    val selectedIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar(modifier = modifier) {
        bottomNavItems.forEachIndexed { index, bottomNavItem ->
            NavigationBarItem(
                selected = selectedIndex.intValue == index,
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
                                imageVector = if (selectedIndex.intValue == index) {
                                    bottomNavItem.selectedIcon
                                } else {
                                    bottomNavItem.unselectedIcon
                                },
                                contentDescription = bottomNavItem.label
                            )
                        }
                    )

                },
                label = { Text(text = bottomNavItem.label) }
            )
        }
    }
}

data class BottomNavItem(
    val label: String,
    val hasUpdates: Boolean,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
)

val bottomNavItems = listOf(
    BottomNavItem(
        label = "Chats",
        selectedIcon = Icons.Filled.MailOutline,
        unselectedIcon = Icons.Outlined.MailOutline,
        badgeCount = 6,
        hasUpdates = false
    ),
    BottomNavItem(
        label = "Updates",
        selectedIcon = Icons.Filled.DateRange,
        unselectedIcon = Icons.Outlined.DateRange,
        hasUpdates = false
    ),
    BottomNavItem(
        label = "Communities",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        hasUpdates = false
    ),
    BottomNavItem(
        label = "Calls",
        selectedIcon = Icons.Filled.Call,
        unselectedIcon = Icons.Outlined.Call,
        hasUpdates = false
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