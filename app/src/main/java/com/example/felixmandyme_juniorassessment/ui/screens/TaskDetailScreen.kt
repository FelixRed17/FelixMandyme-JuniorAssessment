package com.example.felixmandyme_juniorassessment.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.felixmandyme_juniorassessment.ui.viewmodels.TaskDetailViewModel
import kotlinx.coroutines.launch

@Composable
fun TaskDetailScreen(
    viewModel: TaskDetailViewModel = viewModel(factory = TaskDetailViewModel.Factory),
    navigateUp: () -> Unit
){
    val uiState by viewModel.taskDetailUiState.collectAsState()
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(uiState.taskDetails.title,
            style = MaterialTheme.typography.headlineLarge
        )
        Text(text = "TODO DETAILS")
        Text(uiState.taskDetails.description)
        Row {
            Button(onClick = {
                deleteConfirmationRequired = true
            }) {
                Text(text = "Delete")
            }

            Button(onClick = {

            }) {
                Text(text = "Edit")
            }
        }
        if(deleteConfirmationRequired){
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    coroutineScope.launch {
                        viewModel.deleteTask()
                        navigateUp()
                    }
                },
                onDeleteCancel = {deleteConfirmationRequired = false}
            )
        }
    }
}

@Composable
fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = {  },
        title = { Text("Delete TODO") },
        text = { Text("Are you sure you want to delete?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text("No")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text("Yes")
            }
        })
}