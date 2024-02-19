package com.arfest.githubprofiles.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arfest.githubprofiles.core.design.theme.GitAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitProfileApp()
        }
    }
}

@Composable
fun GitProfileApp() {
    GitAppTheme {
        GitProfileHost()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GitProfileApp()
}