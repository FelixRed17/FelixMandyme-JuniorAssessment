package com.example.felixmandyme_juniorassessment.ui.screens.completetasklistscreen.maincomponents

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.felixmandyme_juniorassessment.R
import com.example.felixmandyme_juniorassessment.domain.model.Tasks
import com.example.felixmandyme_juniorassessment.ui.screens.completetasklistscreen.subcomponents.CompletedTaskCard

@Composable
fun CompletedTasksBody(taskList: List<Tasks>, onTaskClick: (Tasks) -> Unit){
    Column {
        if(taskList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_task)
            )
        }else{
            LazyColumn {
                items(items = taskList, key = {it.id}) { task ->
                    CompletedTaskCard(
                        tasks = task,
                        modifier = Modifier.clickable(
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



