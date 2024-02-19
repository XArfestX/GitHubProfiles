package com.arfest.githubprofiles.presentation.profile.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.arfest.githubprofiles.core.navigation.Destination
import com.arfest.githubprofiles.di.DiProvider
import com.arfest.githubprofiles.presentation.profile.ProfileScreen
import com.arfest.githubprofiles.presentation.profile.ProfileViewModel

const val USER_PROFILE_GRAPH = "user_profile_graph"

data object UserProfileDestination : Destination {
    override val route = "$USER_PROFILE_GRAPH/user"
}

fun NavHostController.navigateToUserProfile(login: String) {
    navigate(UserProfileDestination.route)
}

interface UserProfileNavigator{
    fun onNavigateToBack()
}

fun NavHostController.toBack(): UserProfileNavigator =
    object : UserProfileNavigator {
        override fun onNavigateToBack() {
            popBackStack()
        }

    }


fun NavGraphBuilder.userProfile(extuernalNavigator: UserProfileNavigator) {

    navigation(startDestination = UserProfileDestination.route, route = USER_PROFILE_GRAPH) {
        composable(route = UserProfileDestination.route) {
            val viewModel: ProfileViewModel = viewModel(
                factory = ProfileViewModel.Factory(
                    getUserProfileUseCase = DiProvider.appComponent.getUserProfileUseCase,
                    getUserFollowersUseCase = DiProvider.appComponent.getUserFollowersUseCase
                )
            )

            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            ProfileScreen(
                uiState = uiState,
                onBack = extuernalNavigator::onNavigateToBack
            )
        }
        // This is an example of the promo as an inner page
        // promotion()
    }

}