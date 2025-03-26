package com.marcosval.marvel.ui.nav

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object CharacterList : Screen("characterList")
    data object CharacterDetail : Screen("characterDetail/{characterId}") {
        fun createRoute(characterId: Int) = "characterDetail/$characterId"
    }
}