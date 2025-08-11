package com.example.felixmandyme_juniorassessment.ui.screens

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.felixmandyme_juniorassessment.data.Tasks

@Composable
fun CompletedTasksBody( taskList: List<Tasks>, onTaskClick: (Tasks) -> Unit){
    Column {
        if(taskList.isEmpty()) {
            Text(
                text = "No task, Tap + below to add task"
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


@Composable
fun CompletedTaskCard(tasks: Tasks, modifier: Modifier = Modifier){
    Card(
        modifier = modifier.fillMaxWidth()
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

    ) {
        Column(
            modifier = Modifier.padding(10.dp),
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

@Composable
fun CompletedTaskAlertDialog(
    tasks: Tasks,
    onDismiss: () -> Unit,
    onMarkAsIncomplete: (Tasks) -> Unit,
    onDelete: (Tasks) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Completed Task") },
        text = { Text("What would you like to do with this completed task?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        confirmButton = {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        onDelete(tasks)
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text(
                        text = "Delete",
                        color = Color.White
                    )
                }

                Button(
                    onClick = {
                        onMarkAsIncomplete(tasks)
                        onDismiss()
                    }
                ) {
                    Text("Mark Incomplete")
                }
            }
        })
}