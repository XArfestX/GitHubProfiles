package com.arfest.githubprofiles.core.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

interface Destination{
    val route: String
}

data class TopDestinationsCollection(val items: List<TopLevelDestination>)

interface TopLevelDestinationWithCount{
    val badgeValue: Int
    fun copyWithNewBadge(value: Int): TopLevelDestination
}

interface TopLevelDestination{
    val graph: String
    @get:DrawableRes
    val iconId: Int
    @get:StringRes
    val titleId: Int
}

fun NavHostController.navigateSingleTopTo(route: String) {
    navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}