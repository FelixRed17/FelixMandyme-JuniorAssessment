package com.example.felixmandyme_juniorassessment.ui.screens.onboarding_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavKey
import com.example.felixmandyme_juniorassessment.domain.repository.DataStoreRepository
import com.example.felixmandyme_juniorassessment.navigation.NavScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartUpViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _startScreen = MutableStateFlow<NavKey?>(null)
    val startScreen: StateFlow<NavKey?> = _startScreen

    init {
        viewModelScope.launch {
            dataStoreRepository.readOnBoardingState.collect { completed ->
                _startScreen.value = if (completed) NavScreens.TodoScreen else NavScreens.OnBoardingScreen
            }
        }
    }
}