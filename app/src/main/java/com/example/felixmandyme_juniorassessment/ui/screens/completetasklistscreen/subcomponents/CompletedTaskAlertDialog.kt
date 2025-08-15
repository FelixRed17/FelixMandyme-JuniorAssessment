package com.example.felixmandyme_juniorassessment.ui.screens.completetasklistscreen.subcomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.felixmandyme_juniorassessment.R
import com.example.felixmandyme_juniorassessment.domain.model.Tasks

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
        title = { Text(stringResource(R.string.alertDialogTitle)) },
        text = { Text(stringResource(R.string.alertDialogDescription)) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.alertDialogCancel))
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
                        text = stringResource(R.string.alertDialogDelete),
                        color = Color.White
                    )
                }

                Button(
                    onClick = {
                        onMarkAsIncomplete(tasks)
                        onDismiss()
                    }
                ) {
                    Text(stringResource(R.string.alertDialogMarkIncomplete))
                }
            }
        })
}