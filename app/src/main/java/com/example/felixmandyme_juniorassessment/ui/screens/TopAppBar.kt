package com.example.felixmandyme_juniorassessment.ui.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.felixmandyme_juniorassessment.R

enum class TodoScreen(@StringRes val title: Int){
    Start(R.string.TodoApp),
    Add(R.string.addScreen)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    todoScreen: TodoScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
){
    CenterAlignedTopAppBar(
        title = { Text(stringResource(todoScreen.title)) },
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back to previous"
                    )
                }
            }
        }
    )
}