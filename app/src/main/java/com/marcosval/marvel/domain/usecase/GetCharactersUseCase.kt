package com.marcosval.marvel.domain.usecase

import com.marcosval.marvel.domain.model.MarvelCharacter
import com.marcosval.marvel.domain.repository.MarvelRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: MarvelRepository) {
    suspend operator fun invoke(limit: Int, offset: Int): List<MarvelCharacter> {
        return repository.getCharacters(limit, offset)
    }
}