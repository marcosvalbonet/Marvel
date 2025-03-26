package com.marcosval.marvel.data.repository

import com.marcosval.marvel.data.api.MarvelApiService
import com.marcosval.marvel.data.api.MarvelAuth
import com.marcosval.marvel.data.api.toDomain
import com.marcosval.marvel.domain.model.Comic
import com.marcosval.marvel.domain.model.MarvelCharacter
import com.marcosval.marvel.domain.model.MarvelCharacterDetails
import com.marcosval.marvel.domain.repository.MarvelRepository

class MarvelRepositoryImpl(private val apiService: MarvelApiService) : MarvelRepository {

    override suspend fun getCharacters(limit: Int, offset: Int): List<MarvelCharacter> {
        try {
            val authParams = MarvelAuth.generateAuthParams()
            val ts = authParams["ts"] ?: ""
            val publicKey = authParams["apikey"] ?: ""
            val hash = authParams["hash"] ?: ""

            return apiService.getCharacters(limit, offset, ts, publicKey, hash).data.results.map { it.toDomain() }
        }catch (e : Exception) {
            return listOf(
                MarvelCharacter(1, "Batman", "El caballero de la noche", "https://picsum.photos/200/300"),
                MarvelCharacter(2, "Superman", "El super heroe", "https://picsum.photos/200/300"),
                MarvelCharacter(2, "Spiderman", "El hombre araña", "https://picsum.photos/200/300"),
            )
        }
    }

    override suspend fun getCharacterDetail(characterId: Int): MarvelCharacterDetails {
        try {
            val authParams = MarvelAuth.generateAuthParams()
            val ts = authParams["ts"] ?: ""
            val publicKey = authParams["apikey"] ?: ""
            val hash = authParams["hash"] ?: ""
            return apiService.getCharacterDetail(characterId, ts, publicKey, hash).data.results.map { it.toDomain() }.first()
        } catch (e : Exception) {
            return MarvelCharacterDetails(
                1,
                "Batman",
                "El caballero de la noche",
                "1",
                "https://picsum.photos/600/300",
                "",
                listOf(
                    Comic("https://picsum.photos/200/300", "Batman begins"),
                    Comic("https://picsum.photos/200/300", "Batman returns")
                )
            )
        }
    }

}