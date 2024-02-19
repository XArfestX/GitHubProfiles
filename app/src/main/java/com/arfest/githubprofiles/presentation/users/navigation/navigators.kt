package com.arfest.githubprofiles.presentation.users.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.arfest.githubprofiles.core.navigation.Destination
import com.arfest.githubprofiles.di.DiProvider
import com.arfest.githubprofiles.presentation.profile.navigation.navigateToUserProfile
import com.arfest.githubprofiles.presentation.users.UsersScreen
import com.arfest.githubprofiles.presentation.users.UsersViewModel

const val USERS_GRAPH = "users_graph"

data object StartDestination : Destination {
    override val route = USERS_GRAPH
}

data object UsersDestination : Destination {
    override val route = "$USERS_GRAPH/user"
}

interface StartNavigator{
    fun onNavigateStarted(login: String)
}

fun NavHostController.startNavigator(): StartNavigator =
    object : StartNavigator {
        override fun onNavigateStarted(login: String) {
            navigateToUserProfile(login)
        }
    }

interface UserNavigator{
    fun navigateToDetails()
}

fun NavGraphBuilder.users(extuernalNavigator: StartNavigator) {

    navigation(startDestination = UsersDestination.route, route = USERS_GRAPH) {
        composable(route = UsersDestination.route) {
            val viewModel: UsersViewModel = viewModel(
                factory = UsersViewModel.Factory(
                    getUsersUseCase = DiProvider.appComponent.getUsersUseCase,
                    getUserProfileUseCase = DiProvider.appComponent.getUserProfileUseCase
                )
            )

            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            UsersScreen(
                uiState = uiState,
                openDetails = extuernalNavigator::onNavigateStarted,
                getUserProfile = viewModel::openToDetails
            )
        }
        // This is an example of the promo as an inner page
        // promotion()
    }

}