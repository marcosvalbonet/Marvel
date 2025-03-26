package com.marcosval.marvel.domain.usecase

import com.marcosval.marvel.domain.model.MarvelCharacterDetails
import com.marcosval.marvel.domain.repository.MarvelRepository
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(
    private val repository: MarvelRepository
) {
    suspend operator fun invoke(characterId: Int): MarvelCharacterDetails {
        return repository.getCharacterDetail(characterId)
    }
}