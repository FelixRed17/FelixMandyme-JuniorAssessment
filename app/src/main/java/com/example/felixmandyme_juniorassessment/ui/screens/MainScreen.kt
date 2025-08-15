package com.example.felixmandyme_juniorassessment.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.felixmandyme_juniorassessment.domain.model.Tasks
import com.example.felixmandyme_juniorassessment.ui.screens.completetasklistscreen.subcomponents.CompletedTaskAlertDialog
import com.example.felixmandyme_juniorassessment.ui.screens.entertaskform.maincomponents.EnterTaskBottomSheet
import com.example.felixmandyme_juniorassessment.ui.screens.tabrowscreen.TabRow
import com.example.felixmandyme_juniorassessment.ui.screens.weatherscreen.WeatherCard
import com.example.felixmandyme_juniorassessment.ui.screens.weatherscreen.WeatherViewModel

@Composable
fun MainScreen(){
    val selectedTask = remember { mutableStateOf<Tasks?>(null) }
    val viewModel: MainScreenViewModel = hiltViewModel()
    val weatherViewModel: WeatherViewModel = hiltViewModel()
    val showBottomSheet = remember { mutableStateOf(false) }
    val showAlertDialog = remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton ={
            FloatingActionButton(onClick =
                {
                    showBottomSheet.value = true
                    selectedTask.value = null
                }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Task", modifier = Modifier.size(35.dp))
            }
        },
    ) {innerpadding ->
        Surface(
            modifier = Modifier
                .padding(innerpadding)
        ) {
            Column {
                WeatherCard(weatherUiState = weatherViewModel.weatherUiState)
                Spacer(modifier = Modifier.padding(5.dp))
                TabRow(
                    onTaskClick = { task ->
                    selectedTask.value = task
                    showBottomSheet.value = true
                },
                    onCompleteTask = {
                            task ->
                        selectedTask.value = task
                        showAlertDialog.value = true
                    }
                )
            }
            if(showBottomSheet.value){
                EnterTaskBottomSheet(
                    showBottomSheet = showBottomSheet,
                    existingTask = selectedTask.value
                )
            }
            if (showAlertDialog.value && selectedTask.value != null) {
                CompletedTaskAlertDialog(
                    tasks = selectedTask.value!!,
                    onDismiss = { showAlertDialog.value = false },
                    onDelete = {
                        viewModel.deleteTask(it)
                        showAlertDialog.value = false
                    },
                    onMarkAsIncomplete = {
                        viewModel.markTaskDone(it, false)
                        showAlertDialog.value = false
                    }
                )
            }
        }
    }
}
