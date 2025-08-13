package com.example.felixmandyme_juniorassessment.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.felixmandyme_juniorassessment.data.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {
    fun saveOnBoardingState(completed: Boolean){
        viewModelScope.launch {
            dataStoreRepository.saveOnBoardingState(completed)
        }
    }
}