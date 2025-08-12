package com.example.felixmandyme_juniorassessment.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.felixmandyme_juniorassessment.data.RoomDatabaseRepository
import com.example.felixmandyme_juniorassessment.data.TaskDetails
import com.example.felixmandyme_juniorassessment.data.Tasks
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class TaskUiState(
    val taskDetails: TaskDetails = TaskDetails()
)

@HiltViewModel
class TaskFormViewModel @Inject constructor(private val roomDatabaseRepository: RoomDatabaseRepository): ViewModel() {
    var taskUiState by mutableStateOf(TaskUiState())
        private set

    suspend fun saveItem(){
        val task = taskUiState.taskDetails.toTasks()
        if (task.id == 0) {
            roomDatabaseRepository.insertTask(task)
        } else {
            roomDatabaseRepository.updateTask(task)
        }
    }

    fun updateUiState(taskDetails: TaskDetails){
        taskUiState = TaskUiState(taskDetails)
    }

    fun clearUiState() {
        taskUiState = TaskUiState()
    }

}

fun TaskDetails.toTasks(): Tasks = Tasks(
    id = id,
    title = title,
    description = description,
    complete = complete
)

fun Tasks.toTaskDetails(): TaskDetails = TaskDetails(
    id = id,
    title = title,
    description = description,
    complete = complete
)


