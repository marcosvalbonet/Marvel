package com.marcosval.marvel.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.marcosval.marvel.ui.nav.MarvelNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelAppScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Marvel App") }) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            MarvelNavHost(navController)
        }
    }
}