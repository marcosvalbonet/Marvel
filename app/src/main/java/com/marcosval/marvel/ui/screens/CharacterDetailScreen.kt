package com.marcosval.marvel.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.marcosval.marvel.domain.model.Comic
import com.marcosval.marvel.ui.viewmodels.CharacterDetailViewModel


@Composable
fun CharacterDetailScreen(
    characterId: Int,
    viewModel: CharacterDetailViewModel = hiltViewModel(),
) {
    val character by viewModel.character.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(characterId) {
        viewModel.getCharacterDetail(characterId)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            isLoading -> CircularProgressIndicator()
            errorMessage != null -> Text(text = errorMessage ?: "Error desconocido", color = Color.Red)
            character != null -> {
                val details = character
                Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    if (details != null) {
                        Image(
                            painter = rememberAsyncImagePainter(details.thumbnail),
                            contentDescription = details.name,
                            modifier = Modifier.size(200.dp)
                        )
                        Text(text = details.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Text(text = details.description)

                        ComicsList(details.comics)
                    }

                }
            }
        }
    }

}

@Composable
fun ComicsList(comics: List<Comic>) {
    Text(text = "Comics", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
    Column(modifier = Modifier.padding(16.dp)) {
        LazyRow {
            items(comics) { comic ->
                ComicItemView(comic)
            }
        }
    }
}

@Composable
fun ComicItemView(comic: Comic) {
    Column(
        modifier = Modifier
            .width(120.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(comic.resourceURI),
            contentDescription = comic.name,
            modifier = Modifier.size(100.dp)
        )
        Text(text = comic.name, maxLines = 1)
    }
}