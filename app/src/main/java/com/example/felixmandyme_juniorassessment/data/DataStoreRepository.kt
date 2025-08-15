package com.example.felixmandyme_juniorassessment.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.io.IOException


interface DataStoreRepository{
    suspend fun saveOnBoardingState(completed: Boolean)
    val readOnBoardingState: Flow<Boolean>
}

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): DataStoreRepository {
    private companion object{
        val onBoardingKey = booleanPreferencesKey(name = "on_boarding_completed")
        const val TAG = "UserPreferencesRepo"
    }

    override suspend fun saveOnBoardingState(completed: Boolean){
        dataStore.edit { preferences ->
            preferences[onBoardingKey] = completed

        }
    }

    override val readOnBoardingState: Flow<Boolean> = dataStore.data
        .catch {
            if(it is IOException){
                Log.e(TAG, "Error reading preferences", it)
                emit(emptyPreferences())
            }else{
                throw(it)
            }
        }
        .map { preferences ->
            val onBoardingState = preferences[onBoardingKey] ?: false
            onBoardingState
        }
}

class FakeDataStoreRepository: DataStoreRepository{
    private var completed = false

    override suspend fun saveOnBoardingState(completed: Boolean) {
        this.completed = completed
    }

    override val readOnBoardingState: Flow<Boolean> = flowOf(completed)

}