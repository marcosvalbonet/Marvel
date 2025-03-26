package com.marcosval.marvel.domain.di

import com.marcosval.marvel.domain.repository.MarvelRepository
import com.marcosval.marvel.domain.usecase.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideListUseCase(marvelRepository: MarvelRepository) =
        GetCharactersUseCase(marvelRepository)

}