package com.example.felixmandyme_juniorassessment.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.felixmandyme_juniorassessment.data.Tasks
import com.example.felixmandyme_juniorassessment.ui.viewmodels.MainScreenViewModel
import com.example.felixmandyme_juniorassessment.ui.viewmodels.WeatherViewModel


@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = viewModel(factory = MainScreenViewModel.Factory),
    onAddButtonClick: () -> Unit
){
    val mainScreenUiState by viewModel.mainScreenUiState.collectAsState()
    val weatherViewModel: WeatherViewModel = viewModel(
        factory = WeatherViewModel.Factory
    )
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddButtonClick
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Task")
            }
        }
    ) {innerpadding ->
        Surface(
            modifier = Modifier
                .padding(innerpadding)
        ) {
            Column {
                WeatherCard(weatherUiState = weatherViewModel.weatherUiState)
                Spacer(modifier = Modifier.padding(5.dp))
                MainScreenContent(
                    taskList = mainScreenUiState.tasklist,
                    onTaskClick = {}
                )
            }
        }
    }
}

@Composable
fun MainScreenContent(
    taskList: List<Tasks>,
    onTaskClick: (Tasks) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    Column(

    ){
        if(taskList.isEmpty()){
            Text(
                text = "No task, Tap + below to add task"
            )
        }else{

            LazyColumn(
                contentPadding = contentPadding
            ) {
                items(items = taskList, key = {it.id}){ task ->
                    TaskCard(
                        tasks = task,
                        modifier = Modifier.padding(8.dp)
                            .clickable { onTaskClick(task) }
                    )
                }
            }
        }
    }

}

@Composable
fun TaskCard(tasks: Tasks, modifier: Modifier =Modifier){
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
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = tasks.description,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun TaskCardPreview(){
    TaskCard(tasks = Tasks(1, "One piece", "his is a longer piece of text that exceeds the maximum number of lines and will be truncated with an ellipsis.  This is a longer piece of text that exceeds the maximum number of lines and will be truncated with an ellipsis."))
}