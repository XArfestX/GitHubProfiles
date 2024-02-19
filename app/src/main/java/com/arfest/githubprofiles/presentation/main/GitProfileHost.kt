package com.arfest.githubprofiles.presentation.main

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.arfest.githubprofiles.presentation.profile.navigation.toBack
import com.arfest.githubprofiles.presentation.profile.navigation.userProfile
import com.arfest.githubprofiles.presentation.users.navigation.StartDestination
import com.arfest.githubprofiles.presentation.users.navigation.startNavigator
import com.arfest.githubprofiles.presentation.users.navigation.users

@Composable
fun GitProfileHost(
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = StartDestination.route
    ) {
        users(navController.startNavigator())
        userProfile(navController.toBack())
    }
}