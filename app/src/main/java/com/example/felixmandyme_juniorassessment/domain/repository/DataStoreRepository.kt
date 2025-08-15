package com.example.felixmandyme_juniorassessment.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


interface DataStoreRepository{
    suspend fun saveOnBoardingState(completed: Boolean)
    val readOnBoardingState: Flow<Boolean>
}


class FakeDataStoreRepository: DataStoreRepository {
    private var completed = false

    override suspend fun saveOnBoardingState(completed: Boolean) {
        this.completed = completed
    }

    override val readOnBoardingState: Flow<Boolean> = flowOf(completed)

}