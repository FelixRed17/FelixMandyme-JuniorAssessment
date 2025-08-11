package com.example.felixmandyme_juniorassessment.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.felixmandyme_juniorassessment.R
import com.example.felixmandyme_juniorassessment.data.TaskDetails
import com.example.felixmandyme_juniorassessment.data.Tasks
import com.example.felixmandyme_juniorassessment.ui.viewmodels.TaskFormViewModel
import com.example.felixmandyme_juniorassessment.ui.viewmodels.TaskUiState
import com.example.felixmandyme_juniorassessment.ui.viewmodels.toTaskDetails
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterTaskBottomSheet(
    viewModel: TaskFormViewModel = viewModel(factory = TaskFormViewModel.Factory),
    showBottomSheet: MutableState<Boolean>,
    existingTask: Tasks? = null,
){
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    LaunchedEffect(existingTask) {
        existingTask?.let {
            viewModel.updateUiState(it.toTaskDetails())
        }
        sheetState.show()
    }
    ModalBottomSheet(
        onDismissRequest = {
            coroutineScope.launch {
                sheetState.hide()
                viewModel.clearUiState()
                showBottomSheet.value = false
            }
        },
        sheetState = sheetState
    ) {
        TaskForm(
            taskUiState = viewModel.taskUiState,
            onValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveItem()
                    sheetState.hide()
                    viewModel.clearUiState()
                }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet.value = false
                    }
                }
            }
        )
    }
}



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
            placeholder = { Text("Title") },
            singleLine = true,
        )
        OutlinedTextField(
            value = taskUiState.taskDetails.description,
            onValueChange = {onValueChange(taskUiState.taskDetails.copy(description = it))},
            label = { Text(stringResource(R.string.text_box_title)) },
            placeholder = { Text("Description") },
            singleLine = false,
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = onSaveClick) {
            Text(stringResource(R.string.save_task))
        }
    }

}

