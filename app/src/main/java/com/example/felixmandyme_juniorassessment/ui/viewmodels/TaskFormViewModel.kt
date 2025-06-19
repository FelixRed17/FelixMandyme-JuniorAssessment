package com.example.felixmandyme_juniorassessment.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.felixmandyme_juniorassessment.TodoApplication
import com.example.felixmandyme_juniorassessment.data.TaskDetails
import com.example.felixmandyme_juniorassessment.data.Tasks
import com.example.felixmandyme_juniorassessment.data.TasksRepository

data class TaskUiState(
    val taskDetails: TaskDetails = TaskDetails()
)

class TaskFormViewModel(private val tasksRepository: TasksRepository): ViewModel() {
    var taskUiState by mutableStateOf(TaskUiState())
        private set

    suspend fun saveItem(){
        tasksRepository.insertTask(taskUiState.taskDetails.toTasks())
    }

    fun updateUiState(taskDetails: TaskDetails){
        taskUiState = TaskUiState(taskDetails)
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TodoApplication)
                val tasksRepository = application.container.tasksRepository
                TaskFormViewModel(tasksRepository = tasksRepository)
            }
        }
    }


}

fun TaskDetails.toTasks(): Tasks = Tasks(
    id = id,
    title = title,
    description = description,
    complete = complete
)

