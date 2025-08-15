package com.example.felixmandyme_juniorassessment.ui.screens.incompletetasklistscreen.maincomponents

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.felixmandyme_juniorassessment.R
import com.example.felixmandyme_juniorassessment.domain.model.Tasks
import com.example.felixmandyme_juniorassessment.ui.screens.incompletetasklistscreen.subcomponents.IncompleteTaskCard
import com.example.felixmandyme_juniorassessment.ui.screens.swipetodeleteorcomplete.OnSwipeDeleteOrMarkDone

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
                        IncompleteTaskCard(
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

