package com.example.felixmandyme_juniorassessment.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.felixmandyme_juniorassessment.data.Tasks
import com.example.felixmandyme_juniorassessment.ui.viewmodels.MainScreenViewModel

@Composable
fun SegmentedButtonContent(
    onTaskClick: (Tasks) -> Unit,
    onCompleteTask: (Tasks) -> Unit,
    viewModel: MainScreenViewModel = viewModel(factory = MainScreenViewModel.Factory)
) {
    val mainScreenUiState by viewModel.mainScreenUiState.collectAsState()
    val completeUiState by viewModel.completeUiState.collectAsState()
    val options = listOf("Incomplete", "Complete")
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column {
        TabRow(
            selectedTabIndex = selectedIndex,
            contentColor = MaterialTheme.colorScheme.secondary
        ) {
            options.forEachIndexed { index, option ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    text = {
                        Text(option)
                    }
                )
            }
        }

        when (selectedIndex) {
            0 -> IncompleteTasksBody(
                taskList = mainScreenUiState.tasklist,
                taskComplete = {task, isChecked -> viewModel.markTaskDone(task, isChecked)},
                onTaskClick = onTaskClick,
                taskDelete = {task -> viewModel.deleteTask(task)}
            )
            1 -> CompletedTasksBody (completeUiState.tasklist, onCompleteTask)

        }
    }
}



