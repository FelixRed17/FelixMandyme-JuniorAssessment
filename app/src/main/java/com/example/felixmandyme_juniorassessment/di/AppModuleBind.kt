package com.example.felixmandyme_juniorassessment.di

import com.example.felixmandyme_juniorassessment.data.DataStoreRepository
import com.example.felixmandyme_juniorassessment.data.DataStoreRepositoryImpl
import com.example.felixmandyme_juniorassessment.data.NetworkWeatherRepository
import com.example.felixmandyme_juniorassessment.data.RoomDatabaseRepository
import com.example.felixmandyme_juniorassessment.data.RoomDatabaseRepositoryImpl
import com.example.felixmandyme_juniorassessment.data.WeatherRepository
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
        impl: NetworkWeatherRepository
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
