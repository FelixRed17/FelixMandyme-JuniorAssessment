package com.example.felixmandyme_juniorassessment.ui.screens.entertaskform.maincomponents

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.felixmandyme_juniorassessment.domain.model.Tasks
import com.example.felixmandyme_juniorassessment.ui.screens.entertaskform.subcomponents.TaskForm
import com.example.felixmandyme_juniorassessment.ui.screens.entertaskform.TaskFormViewModel
import com.example.felixmandyme_juniorassessment.ui.screens.entertaskform.toTaskDetails
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterTaskBottomSheet(
    viewModel: TaskFormViewModel = hiltViewModel(),
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





