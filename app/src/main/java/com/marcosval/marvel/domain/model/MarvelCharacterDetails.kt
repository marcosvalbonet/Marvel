package com.marcosval.marvel.domain.model

data class MarvelCharacterDetails(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: String,
    val resourceURI: String,
    val comics: List<Comic>,
)

data class Comic(
    val resourceURI: String,
    val name: String
)