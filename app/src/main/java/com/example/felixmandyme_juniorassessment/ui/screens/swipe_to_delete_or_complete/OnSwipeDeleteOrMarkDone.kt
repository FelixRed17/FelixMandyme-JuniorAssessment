package com.example.felixmandyme_juniorassessment.ui.screens.swipe_to_delete_or_complete

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue.EndToStart
import androidx.compose.material3.SwipeToDismissBoxValue.Settled
import androidx.compose.material3.SwipeToDismissBoxValue.StartToEnd
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.felixmandyme_juniorassessment.domain.model.Tasks

@Composable
fun OnSwipeDeleteOrMarkDone(
    tasks: Tasks,
    swipeDone: (Tasks) -> Unit,
    swipeDelete: (Tasks) -> Unit,
    content: @Composable () -> Unit
){
    val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            when (it) {
                StartToEnd -> {
                    swipeDone(tasks)
                    false
                }
                EndToStart -> {
                    swipeDelete(tasks)
                    true
                }
                else -> false
            }
        }
    )

    SwipeToDismissBox(
        state = swipeToDismissBoxState,
        modifier = Modifier.fillMaxSize(),
        backgroundContent = {
            when(swipeToDismissBoxState.dismissDirection){
                StartToEnd -> {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Done",
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Green)
                            .wrapContentSize(CenterStart)
                            .padding(12.dp),
                        tint = Color.White
                    )
                }
                EndToStart -> {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete to do",
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Red)
                            .wrapContentSize(CenterEnd)
                            .padding(12.dp),
                        tint = Color.White
                    )
                }

                Settled -> {}
            }
        }
    ) {
        content()
    }
}