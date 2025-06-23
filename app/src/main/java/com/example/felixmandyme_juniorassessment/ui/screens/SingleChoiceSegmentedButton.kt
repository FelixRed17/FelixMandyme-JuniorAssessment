package com.example.felixmandyme_juniorassessment.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.felixmandyme_juniorassessment.data.Tasks
import com.example.felixmandyme_juniorassessment.ui.viewmodels.MainScreenViewModel

@Composable
fun SegmentedButtonContent(
    onTaskClick: (Tasks) -> Unit,
    viewModel: MainScreenViewModel = viewModel(factory = MainScreenViewModel.Factory)
) {
    val mainScreenUiState by viewModel.mainScreenUiState.collectAsState()
    val completeUiState by viewModel.completeUiState.collectAsState()
    val options = listOf("Incomplete", "Complete")
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column {
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEachIndexed { index, option ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    onClick = { selectedIndex = index },
                    selected = index == selectedIndex,
                    label = { Text(option) }
                )
            }
        }

        when (selectedIndex) {
            0 -> MainScreenContent(
                taskList = mainScreenUiState.tasklist,
                taskComplete = {task, isChecked -> viewModel.markTaskDone(task, isChecked)},
                onTaskClick = onTaskClick
            )
            1 -> completedTasksMainContent(completeUiState.tasklist)

        }
    }
}

@Composable
fun completedTasksMainContent( taskList: List<Tasks>,){
    Column {
        if(taskList.isEmpty()) {
            Text(
                text = "No task, Tap + below to add task"
            )
        }else{
            LazyColumn {
                items(items = taskList, key = {it.id}) { task ->
                    completedTasks(tasks = task)
                }
            }
        }
    }
}
@Composable
fun completedTasks(tasks: Tasks){
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = tasks.title,
                style = MaterialTheme.typography.headlineLarge.copy(textDecoration = TextDecoration.LineThrough)
            )
            Text(
                text = tasks.description,
                style = MaterialTheme.typography.bodyLarge.copy(textDecoration = TextDecoration.LineThrough),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}