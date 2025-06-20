package com.example.felixmandyme_juniorassessment.ui.screens

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.felixmandyme_juniorassessment.data.Tasks
import com.example.felixmandyme_juniorassessment.ui.viewmodels.MainScreenViewModel
import com.example.felixmandyme_juniorassessment.ui.viewmodels.WeatherViewModel

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = viewModel(factory = MainScreenViewModel.Factory),
    onCardClick: (Tasks) -> Unit,
){

    val mainScreenUiState by viewModel.mainScreenUiState.collectAsState()
    val taskCount by viewModel.taskSum.collectAsState()
    val weatherViewModel: WeatherViewModel = viewModel(
        factory = WeatherViewModel.Factory
    )
    val showBottomSheet = remember { mutableStateOf(false) }
    Scaffold(
        bottomBar = {
            BottomAppBar{
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier.size(60.dp)
                    ) {
                        BadgedBox(badge = { Badge{ Text("$taskCount")} },
                            ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Home",
                                modifier = Modifier.size(35.dp),
                            )
                        }
                    }
                    IconButton(onClick =  {showBottomSheet.value = true},  modifier = Modifier.size(60.dp)) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Task", modifier = Modifier.size(35.dp))
                    }
                    IconButton(onClick = {

                    },
                            modifier = Modifier.size(60.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Completed Task",
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }
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
                    onTaskClick = onCardClick,
                    taskComplete = {task, isChecked -> viewModel.markTaskDone(task, isChecked)}
                )
            }
            if(showBottomSheet.value){
                EnterTaskBottomSheet(showBottomSheet = showBottomSheet)
            }
        }
    }
}

@Composable
fun MainScreenContent(
    taskList: List<Tasks>,
    onTaskClick: (Tasks) -> Unit,
    taskComplete: (Tasks, Boolean) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
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
                        taskComplete = { isChecked ->
                            taskComplete(task, isChecked)
                        },
                        modifier = Modifier.padding(8.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = LocalIndication.current
                            ) {
                                onTaskClick(task)
                            }

                    )
                }
            }
        }
    }

}

@Composable
fun TaskCard(
    tasks: Tasks,
    taskComplete: (Boolean) -> Unit,
    modifier: Modifier =Modifier
){
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row{
            Checkbox(
                checked = tasks.complete,
                onCheckedChange = { isChecked ->
                    taskComplete(isChecked)
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 25.dp)
            )
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
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun TaskCardPreview(){
    //TaskCard(tasks = Tasks(1, "One piece", "his is a longer piece of text that exceeds the maximum number of lines and will be truncated with an ellipsis.  This is a longer piece of text that exceeds the maximum number of lines and will be truncated with an ellipsis."))
}