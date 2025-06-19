package com.example.felixmandyme_juniorassessment.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.felixmandyme_juniorassessment.R
import com.example.felixmandyme_juniorassessment.data.TaskDetails
import com.example.felixmandyme_juniorassessment.ui.viewmodels.TaskFormViewModel
import com.example.felixmandyme_juniorassessment.ui.viewmodels.TaskUiState
import kotlinx.coroutines.launch

@Composable
fun EnterTask(
    viewModel: TaskFormViewModel = viewModel(factory = TaskFormViewModel.Factory),
    navigateUp: () -> Unit
){

    val coroutineScope = rememberCoroutineScope()

    TaskForm(
        taskUiState = viewModel.taskUiState,
        onValueChange = viewModel::updateUiState,
        onSaveClick = {
            coroutineScope.launch {
                    viewModel.saveItem()
                    navigateUp()
            }
        }
    )
}


@Composable
fun TaskForm(
    taskUiState: TaskUiState,
    onValueChange: (TaskDetails) -> Unit = {},
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = taskUiState.taskDetails.title,
            onValueChange = {onValueChange(taskUiState.taskDetails.copy(title = it))},
            label = { Text(stringResource(R.string.text_box_title)) },
            placeholder = { Text("Title") },
            singleLine = true,
        )
        OutlinedTextField(
            value = taskUiState.taskDetails.description,
            onValueChange = {onValueChange(taskUiState.taskDetails.copy(description = it))},
            label = { Text(stringResource(R.string.text_box_title)) },
            placeholder = { Text("Title") },
            singleLine = false,
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = onSaveClick) {
            Text(text = "Add Task")
        }
    }

}

@Preview()
@Composable
fun TaskFormPreview(){
    //TaskForm(taskUiState = TaskUiState(), modifier = Modifier.fillMaxSize())
}