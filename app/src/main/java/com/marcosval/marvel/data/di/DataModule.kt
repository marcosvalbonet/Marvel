package com.marcosval.marvel.data.di

import com.marcosval.marvel.common.BASE_URL
import com.marcosval.marvel.data.api.MarvelApiService
import com.marcosval.marvel.data.repository.MarvelRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideMarvelApiService(): MarvelApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApiService::class.java)
    }

    @Provides
    fun provideRepositoryImpl(marvelApiService: MarvelApiService): MarvelRepositoryImpl {
        return MarvelRepositoryImpl(marvelApiService)
    }

}