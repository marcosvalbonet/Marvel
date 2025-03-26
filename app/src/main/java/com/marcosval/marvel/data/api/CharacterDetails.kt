package com.marcosval.marvel.data.api

import com.marcosval.marvel.domain.model.Comic
import com.marcosval.marvel.domain.model.MarvelCharacterDetails

data class CharacterDetails(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: MarvelThumbnail,
    val resourceURI: String,
    val comics: MarvelCollection,
    val series: MarvelCollection,
    val stories: MarvelStoryCollection,
    val events: MarvelCollection,
    val urls: List<MarvelUrl>
)

data class MarvelThumbnail(
    val path: String,
    val extension: String
) {
    val completeUrl: String
        get() = "$path.$extension".replace("http://", "https://")
}

data class MarvelCollection(
    val available: Int,
    val collectionURI: String,
    val items: List<MarvelItem>
)

data class MarvelStoryCollection(
    val available: Int,
    val collectionURI: String,
    val items: List<MarvelStoryItem>
)

data class MarvelItem(
    val resourceURI: String,
    val name: String
)

data class MarvelStoryItem(
    val resourceURI: String,
    val name: String,
    val type: String
)

data class MarvelUrl(
    val type: String,
    val url: String
)

fun CharacterDetails.toDomain() = MarvelCharacterDetails(
    id = id,
    name = name,
    description = description,
    modified = modified,
    thumbnail = thumbnail.completeUrl,
    resourceURI = resourceURI,
    comics = comics.items.map { Comic(it.resourceURI, it.name) }
)