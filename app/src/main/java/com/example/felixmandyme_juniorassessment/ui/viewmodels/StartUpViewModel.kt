package com.example.felixmandyme_juniorassessment.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavKey
import com.example.felixmandyme_juniorassessment.data.DataStoreRepository
import com.example.felixmandyme_juniorassessment.navigation.NavScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class StartUpViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _startScreen = MutableStateFlow<NavKey?>(null)
    val startScreen: StateFlow<NavKey?> = _startScreen

    init {
        viewModelScope.launch {
            dataStoreRepository.readOnBoardingState.collect { completed ->
                _startScreen.value = if (completed) NavScreens.todoScreen else NavScreens.onBoardingPager
            }
        }
    }
}

