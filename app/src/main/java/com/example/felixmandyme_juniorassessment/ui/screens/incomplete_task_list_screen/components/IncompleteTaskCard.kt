package com.example.felixmandyme_juniorassessment.ui.screens.incomplete_task_list_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.felixmandyme_juniorassessment.domain.model.Tasks


@Composable
fun IncompleteTaskCard(
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