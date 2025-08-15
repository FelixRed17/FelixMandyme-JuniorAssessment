package com.example.felixmandyme_juniorassessment.ui.screens.entertaskform.subcomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.felixmandyme_juniorassessment.R
import com.example.felixmandyme_juniorassessment.domain.model.TaskDetails
import com.example.felixmandyme_juniorassessment.ui.screens.entertaskform.TaskUiState

@Composable
fun TaskForm(
    taskUiState: TaskUiState,
    onValueChange: (TaskDetails) -> Unit = {},
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxWidth()
            .padding(bottom = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = taskUiState.taskDetails.title,
            onValueChange = {onValueChange(taskUiState.taskDetails.copy(title = it))},
            label = { Text(stringResource(R.string.text_box_title)) },
            placeholder = { Text(stringResource(R.string.title)) },
            singleLine = true,
        )
        OutlinedTextField(
            value = taskUiState.taskDetails.description,
            onValueChange = {onValueChange(taskUiState.taskDetails.copy(description = it))},
            label = { Text(stringResource(R.string.text_box_title)) },
            placeholder = { Text(stringResource(R.string.description)) },
            singleLine = false,
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = onSaveClick) {
            Text(stringResource(R.string.save_task))
        }
    }
}