package com.arfest.githubprofiles.presentation.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.arfest.githubprofiles.core.navigation.Destination
import com.arfest.githubprofiles.presentation.users.UsersScreen
import com.arfest.githubprofiles.presentation.users.navigation.StartNavigator
import com.arfest.githubprofiles.presentation.users.navigation.UserNavigator


//private const val ROUTE = "start"
//
//
//data object StartDestination : Destination {
//    override val route = ROUTE
//}




fun NavHostController.usersNavigator(): UserNavigator = object : UserNavigator {

    override fun navigateToDetails() {
        TODO("Not yet implemented")
    }
}


//fun NavGraphBuilder.start(externalNavigator: StartNavigator){
//
//    composable(route = StartDestination.route){
//        UsersScreen(onGetStarted = externalNavigator::onNavigateAfterStarted)
//    }
//}