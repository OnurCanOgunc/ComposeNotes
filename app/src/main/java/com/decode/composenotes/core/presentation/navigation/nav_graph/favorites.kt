package com.decode.composenotes.core.presentation.navigation.nav_graph

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.decode.composenotes.favorites.presentation.FavoritesScreen
import com.decode.composenotes.core.presentation.navigation.Screens

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.favorites(navController: NavController,sharedTransitionScope: SharedTransitionScope) {
    navigation<Screens.Favorite>(startDestination = Screens.Favorite.FavoriteScreen) {
        composable<Screens.Favorite.FavoriteScreen> {
            FavoritesScreen(
                onNavigate = {
                    navController.navigate(it)
                },
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = this,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}