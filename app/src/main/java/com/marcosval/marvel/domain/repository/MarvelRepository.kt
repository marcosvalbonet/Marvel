package com.marcosval.marvel.domain.repository

import com.marcosval.marvel.domain.model.MarvelCharacter
import com.marcosval.marvel.domain.model.MarvelCharacterDetails

interface MarvelRepository {

    suspend fun getCharacters(limit: Int, offset: Int) : List<MarvelCharacter>

    suspend fun getCharacterDetail(characterId: Int): MarvelCharacterDetails
}