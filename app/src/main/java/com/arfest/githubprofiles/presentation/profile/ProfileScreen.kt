package com.arfest.githubprofiles.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.arfest.githubprofiles.R
import com.arfest.githubprofiles.core.design.theme.itemHeight122
import com.arfest.githubprofiles.core.design.theme.itemWidth100
import com.arfest.githubprofiles.core.design.theme.padding10
import com.arfest.githubprofiles.core.design.theme.padding32
import com.arfest.githubprofiles.core.design.theme.padding5
import com.arfest.githubprofiles.core.ui.spanned
import com.arfest.githubprofiles.core.widgets.ItemP
import com.arfest.githubprofiles.core.widgets.LoadingIndicator

@Composable
private fun ScreenContentFollowers(
    modifier: Modifier = Modifier,
    uiState: UserProfileUiState.SuccessFollowers,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(padding32)
    ) {
        items(items = uiState.userProfile) { followers ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = padding10, start = padding5, end = padding5)
            ) {
                ItemP(
                    user = followers,
                    onClick = {},
                )
            }
        }
    }
}

@Composable
private fun ScreenContentBio(
    uiState: UserProfileUiState.SuccessBio,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier,
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable { onClick() }
                    .background(MaterialTheme.colorScheme.background),
            ){
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "icon back",
                )
            }
            Text(
                text = uiState.userProfile.login,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(padding10)
                    .align(Alignment.Center)
            )
        }

        Card {
            Row {
                AsyncImage(
                    modifier = Modifier
                        .width(itemWidth100)
                        .height(itemHeight122)
                        .padding(top = padding10, start = padding10),
                    model = uiState.userProfile.image,
                    contentDescription = uiState.userProfile.login  )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                    text = uiState.userProfile.name,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(padding10)
                        .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = uiState.userProfile.company,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = uiState.userProfile.email,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = uiState.userProfile.blog,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = uiState.userProfile.location,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            Text(
                text = "Информация",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(padding10)
            )
            Text(
                text = uiState.userProfile.bio,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(padding10)
            )
        }
    }
}

@Composable
fun ProfileScreen(
    uiState: UserProfileUiState,
    onBack: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(padding32)
    ) {
        spanned {
            when (uiState) {
                is UserProfileUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingIndicator()
                    }
                }

                is UserProfileUiState.SuccessBio -> {
                    ScreenContentBio(
                        uiState = uiState,
                        onClick = onBack
                    )
                }

                else -> {}
            }
        }
        spanned {
            Column {
                Text(
                    text = "Пользователи",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .padding(top = padding10)
                        .align(Alignment.CenterHorizontally)
                )
                when (uiState) {
                    is UserProfileUiState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            LoadingIndicator()
                        }
                    }

                    is UserProfileUiState.SuccessFollowers -> {
                        ScreenContentFollowers(
                            modifier = Modifier.fillMaxSize(),
                            uiState = uiState,
                        )
                    }

                    else -> {}
                }
            }
        }
    }
}