package com.example.felixmandyme_juniorassessment.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.example.felixmandyme_juniorassessment.data.TasksDao
import com.example.felixmandyme_juniorassessment.data.TasksDatabase
import com.example.felixmandyme_juniorassessment.network.WeatherApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    private val BASE_URL = "https://api.weatherapi.com/"

    private val onBoardingPreferenceName = "onBoardingPreference"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesRetrofitService(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): WeatherApi{
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun providesTasksDao(@ApplicationContext context: Context): TasksDao {
        return TasksDatabase.getDatabase(context).tasksDao()
    }


    @Provides
    @Singleton
    fun providesDataStore(@ApplicationContext context: Context): DataStore<Preferences>{
        return PreferenceDataStoreFactory.create(
            produceFile = {context.dataStoreFile("user_prefs")}
        )
    }
}