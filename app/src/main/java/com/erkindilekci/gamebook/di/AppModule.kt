package com.erkindilekci.gamebook.di

import com.erkindilekci.gamebook.data.remote.api.Api
import com.erkindilekci.gamebook.data.repository.GameRepositoryImpl
import com.erkindilekci.gamebook.domain.repository.GameRepository
import com.erkindilekci.gamebook.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton @Provides
    fun provideApi(): Api {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(Api::class.java)
    }

    @Singleton @Provides
    fun provideRepository(api: Api): GameRepository {
        return GameRepositoryImpl(api)
    }
}