package com.decode.composenotes.core.presentation.navigation.nav_graph

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.decode.composenotes.favorites.presentation.FavoritesScreen
import com.decode.composenotes.core.presentation.navigation.Screens
import com.decode.composenotes.favorites.presentation.FavoriteViewModel
import com.decode.composenotes.notes.presentation.NoteViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.favorites(navController: NavController,sharedTransitionScope: SharedTransitionScope) {
    navigation<Screens.Favorite>(startDestination = Screens.Favorite.FavoriteScreen) {
        composable<Screens.Favorite.FavoriteScreen> {
            val viewModel = hiltViewModel<FavoriteViewModel>()
            val favoriteState = viewModel.uiState.collectAsStateWithLifecycle()
            val searchState = viewModel.searchState.collectAsStateWithLifecycle()
            FavoritesScreen(
                favoriteState = favoriteState.value,
                searchState = searchState.value,
                onUIEvent = viewModel::onEvent,
                searchQuery = viewModel.searchQuery,
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