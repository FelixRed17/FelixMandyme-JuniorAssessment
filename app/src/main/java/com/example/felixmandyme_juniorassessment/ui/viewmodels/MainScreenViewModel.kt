package com.example.felixmandyme_juniorassessment.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.felixmandyme_juniorassessment.TodoApplication
import com.example.felixmandyme_juniorassessment.data.Tasks
import com.example.felixmandyme_juniorassessment.data.TasksRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class MainScreenUiState(val tasklist: List<Tasks> = listOf())

class MainScreenViewModel(tasksRepository: TasksRepository):ViewModel() {
    val mainScreenUiState: StateFlow<MainScreenUiState> = tasksRepository.getAllTasksIncompleted().map {
        MainScreenUiState(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = MainScreenUiState()
    )

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TodoApplication)
                val tasksRepository = application.container.tasksRepository
                MainScreenViewModel(tasksRepository = tasksRepository)
            }
        }
        private const val TIMEOUT_MILLIS = 5_000L
    }
}