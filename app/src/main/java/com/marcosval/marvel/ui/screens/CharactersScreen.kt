package com.marcosval.marvel.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.marcosval.marvel.domain.model.MarvelCharacter
import com.marcosval.marvel.ui.nav.Screen
import com.marcosval.marvel.ui.viewmodels.CharactersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    navController: NavController,
    viewModel: CharactersViewModel = hiltViewModel()
) {
    val characters by viewModel.characters.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        LazyColumn {
            itemsIndexed(characters) { index, character ->
                CharacterItem(character) { characterId ->
                    navController.navigate(Screen.CharacterDetail.createRoute(characterId))
                }

                if (index == characters.lastIndex) {
                    LaunchedEffect(Unit) {
                        viewModel.loadMoreCharacters()
                    }
                }
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(40.dp))
            }
        }

        if (errorMessage != null) {
            Text(text = errorMessage ?: "Error desconocido", color = Color.Red)
        }

    }
}

@Composable
fun CharacterItem(character: MarvelCharacter, onClick: (Int) -> Unit) {
    Row(modifier = Modifier
        .padding(8.dp)
        .clickable { onClick(character.id) }
    ) {
        Image(
            painter = rememberAsyncImagePainter(character.thumbnail),
            contentDescription = character.name,
            modifier = Modifier.size(80.dp)
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(text = character.name, fontWeight = FontWeight.Bold)
            Text(text = character.description, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
    }
}