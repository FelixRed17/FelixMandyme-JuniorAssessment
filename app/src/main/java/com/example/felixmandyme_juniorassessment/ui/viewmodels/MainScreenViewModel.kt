package com.example.felixmandyme_juniorassessment.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.felixmandyme_juniorassessment.domain.repository.RoomDatabaseRepository
import com.example.felixmandyme_juniorassessment.domain.model.Tasks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainScreenUiState(val tasklist: List<Tasks> = listOf())

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val roomDatabaseRepository: RoomDatabaseRepository):ViewModel() {

    val mainScreenUiState: StateFlow<MainScreenUiState> = roomDatabaseRepository.getAllTasksIncompleted().map {
        MainScreenUiState(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = MainScreenUiState()
    )

    val completeUiState: StateFlow<MainScreenUiState> = roomDatabaseRepository.getAllTasksCompleted().map {
        MainScreenUiState(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = MainScreenUiState()
    )

    fun markTaskDone(task: Tasks, complete: Boolean) {
        viewModelScope.launch {
            roomDatabaseRepository.updateTask(task.copy(complete = complete))
        }
    }

    fun deleteTask(task: Tasks) {
        viewModelScope.launch {
            roomDatabaseRepository.deleteTask(task)
        }
    }

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
}






