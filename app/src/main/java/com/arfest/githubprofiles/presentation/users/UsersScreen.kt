package com.arfest.githubprofiles.presentation.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arfest.githubprofiles.core.design.theme.padding10
import com.arfest.githubprofiles.core.design.theme.padding32
import com.arfest.githubprofiles.core.design.theme.padding5
import com.arfest.githubprofiles.core.widgets.Item
import com.arfest.githubprofiles.core.widgets.LoadingIndicator
import kotlin.reflect.KSuspendFunction1

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    uiState: UserUiState.Success,
    onNavigateToDetails: (String) -> Unit,
    getUserProfile: KSuspendFunction1<String, Unit>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(padding32)
    ) {
        items(items = uiState.users) { user ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = padding10, start = padding5, end = padding5)
            ) {
                Item(
                    user = user,
                    onClick = onNavigateToDetails,
                    getProfile = getUserProfile
                )
            }
        }
    }

}

@Composable
fun UsersScreen(
    uiState: UserUiState,
    openDetails: (String) -> Unit,
    getUserProfile: KSuspendFunction1<String, Unit>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(space8)
    ) {
        Column {
            Text(
                text = "Пользователи",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .padding(top = padding10)
                    .align(Alignment.CenterHorizontally)
            )
            when (uiState) {
                is UserUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingIndicator()
                    }
                }
                is UserUiState.Success -> {
                    ScreenContent(
                        modifier = Modifier.fillMaxSize(),
                        uiState = uiState,
                        onNavigateToDetails = openDetails,
                        getUserProfile = getUserProfile
                    )
                }

                else -> {}
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun UserScreenPreview() {
//    UsersScreen(uiState = UserUiState.Success(users =), onGetStarted = {})
//}