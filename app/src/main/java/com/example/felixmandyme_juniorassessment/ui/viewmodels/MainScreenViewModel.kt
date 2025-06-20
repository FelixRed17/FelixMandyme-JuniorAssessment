package com.example.felixmandyme_juniorassessment.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.felixmandyme_juniorassessment.TodoApplication
import com.example.felixmandyme_juniorassessment.data.TaskDetails
import com.example.felixmandyme_juniorassessment.data.Tasks
import com.example.felixmandyme_juniorassessment.data.TasksRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class MainScreenUiState(val tasklist: List<Tasks> = listOf())

class MainScreenViewModel(private val tasksRepository: TasksRepository):ViewModel() {
    val taskSum: StateFlow<Int> = tasksRepository.getAllTasksIncompleteSum()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    val mainScreenUiState: StateFlow<MainScreenUiState> = tasksRepository.getAllTasksIncompleted().map {
        MainScreenUiState(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = MainScreenUiState()
    )

    fun markTaskDone(task: Tasks, complete: Boolean) {
        viewModelScope.launch {
            tasksRepository.updateTask(task.copy(complete = complete))
        }
    }


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



