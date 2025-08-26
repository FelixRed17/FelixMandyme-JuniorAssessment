package com.example.felixmandyme_juniorassessment.ui.screens.incomplete_task_list_screen

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.felixmandyme_juniorassessment.R
import com.example.felixmandyme_juniorassessment.domain.model.Tasks
import com.example.felixmandyme_juniorassessment.ui.screens.incomplete_task_list_screen.components.IncompleteTaskCard
import com.example.felixmandyme_juniorassessment.ui.screens.swipe_to_delete_or_complete.OnSwipeDeleteOrMarkDone

@Composable
fun IncompleteTasksBody(
    taskList: List<Tasks>,
    onTaskClick: (Tasks) -> Unit,
    taskComplete: (Tasks, Boolean) -> Unit,
    taskDelete: (Tasks) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
){
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.emptyghost)
    )


    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    Column(

    ){
        if(taskList.isEmpty()){
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = Modifier.size(150.dp)
                )

                Text(
                    text = stringResource(R.string.no_task),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
            }
        }else{
            LazyColumn(
                contentPadding = contentPadding
            ) {
                items(items = taskList, key = {it.id}){ task ->
                    Spacer(modifier = Modifier.height(10.dp))
                    OnSwipeDeleteOrMarkDone(
                        tasks = task,
                        swipeDone = {taskComplete(it, true)},
                        swipeDelete = {taskDelete(it)}
                    ) {
                        IncompleteTaskCard(
                            tasks = task,
                            taskComplete = { isChecked ->
                                taskComplete(task, isChecked)
                            },
                            modifier = Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = LocalIndication.current
                                ) {
                                    onTaskClick(task)
                                }
                        )
                    }
                }
            }
        }
    }
}

