package com.example.felixmandyme_juniorassessment.ui.screens.completetasklistscreen.subcomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.felixmandyme_juniorassessment.domain.model.Tasks

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