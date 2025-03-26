package com.marcosval.marvel.data.api

import com.marcosval.marvel.domain.model.MarvelCharacter

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)

data class Thumbnail(
    val path: String,
    val extension: String
)

fun Character.toDomain() = MarvelCharacter(
    id = id,
    name = name,
    description = description,
    thumbnail = "${thumbnail.path}.${thumbnail.extension}"
)