package com.example.felixmandyme_juniorassessment.di

import com.example.felixmandyme_juniorassessment.data.local.DataStoreRepositoryImpl
import com.example.felixmandyme_juniorassessment.data.local.RoomDatabaseRepositoryImpl
import com.example.felixmandyme_juniorassessment.data.remote.WeatherRepositoryImpl
import com.example.felixmandyme_juniorassessment.domain.repository.DataStoreRepository
import com.example.felixmandyme_juniorassessment.domain.repository.RoomDatabaseRepository
import com.example.felixmandyme_juniorassessment.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleBind {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        impl: WeatherRepositoryImpl
    ): WeatherRepository

    @Binds
    @Singleton
    abstract fun bindRoomDatabaseRepository(
        impl: RoomDatabaseRepositoryImpl
    ): RoomDatabaseRepository

    @Binds
    @Singleton
    abstract fun bindDataStoreRepository(
        impl: DataStoreRepositoryImpl
    ): DataStoreRepository
}
