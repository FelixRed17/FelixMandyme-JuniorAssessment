package com.example.felixmandyme_juniorassessment.ui.screens

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.felixmandyme_juniorassessment.R
import com.example.felixmandyme_juniorassessment.data.Tasks

@Composable
fun IncompleteTasksBody(
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
                text = stringResource(R.string.no_task)
            )
        }else{
            LazyColumn(
                contentPadding = contentPadding
            ) {
                items(items = taskList, key = {it.id}){ task ->
                    OnSwipeDeleteOrMarkDone(
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
fun TaskCard(
    tasks: Tasks,
    taskComplete: (Boolean) -> Unit,
    modifier: Modifier = Modifier
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