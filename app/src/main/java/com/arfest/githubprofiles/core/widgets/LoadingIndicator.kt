package com.arfest.githubprofiles.core.widgets

import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arfest.githubprofiles.core.design.theme.itemWidth64

@Composable
fun LoadingIndicator() {
    CircularProgressIndicator(
        modifier = Modifier.width(itemWidth64),
        color = MaterialTheme.colorScheme.surfaceVariant,
        trackColor = MaterialTheme.colorScheme.secondary,
    )
}