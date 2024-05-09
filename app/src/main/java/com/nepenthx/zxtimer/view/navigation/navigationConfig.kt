package com.nepenthx.zxtimer.view.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.nepenthx.zxtimer.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {

    // for home
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.test1,
        icon_focused = R.drawable.test1
    )

    // for report
    object Report: BottomBarScreen(
        route = "report",
        title = "Report",
        icon = R.drawable.test1,
        icon_focused = R.drawable.test1
    )

    // for report
    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = R.drawable.test1,
        icon_focused = R.drawable.test1
    )

}
