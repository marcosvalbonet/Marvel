package com.marcosval.marvel.data.di

import com.marcosval.marvel.data.repository.MarvelRepositoryImpl
import com.marcosval.marvel.domain.repository.MarvelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun providesRepository(repositoryImpl: MarvelRepositoryImpl): MarvelRepository
}