package com.example.felixmandyme_juniorassessment.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun TaskDetailScreen(

){
    Column {
        Text("")
        Text(text = "TODO DETAILS")
        Text("")
        Row {
            Button(onClick = {}) {
                Text(text = "Delete")
            }

            Button(onClick = {}) {
                Text(text = "Edit")
            }
        }
    }
}

@Composable
private fun DeleteConfirmationDialog(
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