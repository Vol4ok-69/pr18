package com.example.pr18.ui.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.pr18.ui.screens.MainMenuScreen
import com.example.pr18.ui.screens.PendingIntentScreen
import com.example.pr18.ui.screens.SimpleServiceScreen
import com.example.pr18.ui.screens.StickyServiceScreen
import com.example.pr18.ui.screens.StopServiceScreen

fun androidx.navigation.NavGraphBuilder.pr18Graph(
    navController: NavHostController,
    contentPadding: PaddingValues,
) {
    composable(Pr18Destinations.MainMenu.route) {
        MainMenuScreen(
            contentPadding = contentPadding,
            onOpenSimple = { navController.navigate(Pr18Destinations.SimpleService.route) },
            onOpenStopSelf = { navController.navigate(Pr18Destinations.StopSelf.route) },
            onOpenSticky = { navController.navigate(Pr18Destinations.StickyModes.route) },
            onOpenPending = { navController.navigate(Pr18Destinations.PendingIntent.route) },
        )
    }

    composable(Pr18Destinations.SimpleService.route) {
        SimpleServiceScreen(
            contentPadding = contentPadding,
            onBack = { navController.popBackStack() },
        )
    }

    composable(Pr18Destinations.StopSelf.route) {
        StopServiceScreen(
            contentPadding = contentPadding,
            onBack = { navController.popBackStack() },
        )
    }

    composable(Pr18Destinations.StickyModes.route) {
        StickyServiceScreen(
            contentPadding = contentPadding,
            onBack = { navController.popBackStack() },
        )
    }

    composable(Pr18Destinations.PendingIntent.route) {
        PendingIntentScreen(
            contentPadding = contentPadding,
            onBack = { navController.popBackStack() },
        )
    }
}

