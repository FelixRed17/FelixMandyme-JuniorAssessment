package com.example.felixmandyme_juniorassessment.ui

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.felixmandyme_juniorassessment.ui.screens.EnterTask
import com.example.felixmandyme_juniorassessment.ui.screens.MainScreen
import com.example.felixmandyme_juniorassessment.ui.screens.TaskDetailScreen
import com.example.felixmandyme_juniorassessment.ui.screens.TodoScreen
import com.example.felixmandyme_juniorassessment.ui.screens.TopAppBar


@Composable
fun TodoHome(
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = TodoScreen.valueOf(
        backStackEntry?.destination?.route?.substringBefore("/") ?: TodoScreen.Start.name
    )

    Scaffold(
        topBar = {
            TopAppBar(
                todoScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {navController.navigateUp()}
            )
        }
    ){ innerpadding ->
        NavHost(
            navController = navController,
            startDestination = TodoScreen.Start.name,
            modifier = Modifier
                .padding(
                    start = innerpadding.calculateStartPadding(LayoutDirection.Ltr),
                    end = innerpadding.calculateEndPadding(LayoutDirection.Ltr),
                    bottom = innerpadding.calculateBottomPadding(),
                    top= 60.dp
                )
                .fillMaxSize()
        ){
            composable(route = TodoScreen.Start.name){
                MainScreen(
                    onCardClick = {task ->
                        navController.navigate(route = "${TodoScreen.TodoDetail.name}/${task.id}")
                    }
                )
            }
            composable(route = "${TodoScreen.TodoDetail.name}/{taskId}",
                arguments = listOf(navArgument("taskId") { type = NavType.IntType })){
                TaskDetailScreen(
                    navigateUp = {navController.navigate(TodoScreen.Start.name)}
                )
            }
        }
    }

}