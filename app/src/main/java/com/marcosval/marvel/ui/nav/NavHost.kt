package com.marcosval.marvel.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.marcosval.marvel.ui.screens.CharacterDetailScreen
import com.marcosval.marvel.ui.screens.CharactersScreen
import com.marcosval.marvel.ui.screens.SplashScreen

@Composable
fun MarvelNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) { SplashScreen(navController) }
        composable(Screen.CharacterList.route) {
            CharactersScreen(navController)
        }
        composable(
            route = Screen.CharacterDetail.route,
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: 0
            CharacterDetailScreen(characterId)
        }
    }
}
