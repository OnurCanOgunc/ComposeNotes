package com.decode.composenotes.core.presentation.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.decode.composenotes.core.presentation.navigation.nav_graph.favorites
import com.decode.composenotes.core.presentation.navigation.nav_graph.notes

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation(modifier: Modifier = Modifier,isDarkMode: Boolean) {
    val navController = rememberNavController()
    SharedTransitionLayout {
        NavHost(
            modifier = modifier.fillMaxSize(),
            navController = navController,
            startDestination = Screens.Note,
        ) {
            notes(
                navController = navController,
                sharedTransitionScope = this@SharedTransitionLayout,
                isDarkMode = isDarkMode
            )
            favorites(
                navController = navController,
                sharedTransitionScope = this@SharedTransitionLayout,
            )
        }
    }

}