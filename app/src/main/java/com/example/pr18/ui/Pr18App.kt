package com.example.pr18.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.pr18.ui.nav.Pr18Destinations
import com.example.pr18.ui.nav.pr18Graph

@Composable
fun Pr18App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Pr18Destinations.MainMenu.route,
        modifier = modifier,
    ) {
        pr18Graph(
            navController = navController,
            contentPadding = androidx.compose.foundation.layout.PaddingValues(),
        )
    }
}
