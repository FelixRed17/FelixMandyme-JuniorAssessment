package com.example.felixmandyme_juniorassessment.ui.screens

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue.EndToStart
import androidx.compose.material3.SwipeToDismissBoxValue.Settled
import androidx.compose.material3.SwipeToDismissBoxValue.StartToEnd
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.felixmandyme_juniorassessment.data.Tasks
import com.example.felixmandyme_juniorassessment.ui.viewmodels.MainScreenViewModel
import com.example.felixmandyme_juniorassessment.ui.viewmodels.WeatherViewModel

@Composable
fun MainScreen(){
    val selectedTask = remember { mutableStateOf<Tasks?>(null) }
    val viewModel: MainScreenViewModel = viewModel(factory = MainScreenViewModel.Factory)
    val weatherViewModel: WeatherViewModel = viewModel(
        factory = WeatherViewModel.Factory
    )
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
                SegmentedButtonContent(
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

@Composable
fun MainScreenContent(
    taskList: List<Tasks>,
    onTaskClick: (Tasks) -> Unit,
    taskComplete: (Tasks, Boolean) -> Unit,
    taskDelete: (Tasks) -> Unit,
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
                    TodoSwipe(
                        tasks = task,
                        swipeDone = {taskComplete(it, true)},
                        swipeDelete = {taskDelete(it)}
                    ) {
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
}

@Composable
fun TodoSwipe(
    tasks: Tasks,
    swipeDone: (Tasks) -> Unit,
    swipeDelete: (Tasks) -> Unit,
    content: @Composable () -> Unit
){
    val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            when (it) {
                StartToEnd -> {
                    swipeDone(tasks)
                    false
                }
                EndToStart -> {
                    swipeDelete(tasks)
                    true
                }
                else -> false
            }
        }
    )

    SwipeToDismissBox(
        state = swipeToDismissBoxState,
        modifier = Modifier.fillMaxSize(),
        backgroundContent = {
            when(swipeToDismissBoxState.dismissDirection){
                StartToEnd -> {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Done",
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Green)
                            .wrapContentSize(CenterStart)
                            .padding(12.dp),
                        tint = Color.White
                    )
                }
                EndToStart -> {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete to do",
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Red)
                            .wrapContentSize(CenterEnd)
                            .padding(12.dp),
                        tint = Color.White
                    )
                }

                Settled -> {}
            }
        }
    ) {
        content()
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
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary)

    ) {
        Row{
            Checkbox(
                checked = tasks.complete,
                onCheckedChange = { isChecked ->
                    taskComplete(isChecked)
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 20.dp)
            )
            Column(
                modifier = Modifier.padding(10.dp),
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