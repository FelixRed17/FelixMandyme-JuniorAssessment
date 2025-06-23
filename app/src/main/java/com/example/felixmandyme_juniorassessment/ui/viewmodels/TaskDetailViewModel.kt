package com.example.felixmandyme_juniorassessment.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.felixmandyme_juniorassessment.TodoApplication
import com.example.felixmandyme_juniorassessment.data.TaskDetails
import com.example.felixmandyme_juniorassessment.data.Tasks
import com.example.felixmandyme_juniorassessment.data.TasksRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class TaskDetailUiState(val taskDetails: TaskDetails = TaskDetails())

class TaskDetailViewModel(private val tasksRepository: TasksRepository, savedStateHandle: SavedStateHandle): ViewModel() {

    private val taskId: Int = checkNotNull(savedStateHandle["taskId"])

    val taskDetailUiState: StateFlow<TaskDetailUiState> = tasksRepository.getTask(taskId)
        .filterNotNull()
        .map {
            TaskDetailUiState(it.toTaskDetails())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = TaskDetailUiState()
        )

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TodoApplication)
                val tasksRepository = application.container.tasksRepository
                TaskDetailViewModel(tasksRepository, this.createSavedStateHandle())
            }
        }
    }

    suspend fun deleteTask(){
        tasksRepository.deleteTask(taskDetailUiState.value.taskDetails.toTasks())
    }


}

fun Tasks.toTaskDetails(): TaskDetails = TaskDetails(
    id = id,
    title = title,
    description = description,
    complete = complete
)
