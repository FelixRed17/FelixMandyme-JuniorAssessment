package com.example.felixmandyme_juniorassessment.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.felixmandyme_juniorassessment.ui.screens.EnterTask
import com.example.felixmandyme_juniorassessment.ui.screens.MainScreen
import com.example.felixmandyme_juniorassessment.ui.screens.TodoScreen
import com.example.felixmandyme_juniorassessment.ui.screens.TopAppBar


@Composable
fun TodoHome(
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = TodoScreen.valueOf(
        backStackEntry?.destination?.route ?: TodoScreen.Start.name
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
            modifier = Modifier.padding(innerpadding)
                .fillMaxSize()
        ){
            composable(route = TodoScreen.Start.name){
                MainScreen(onAddButtonClick = {
                    navController.navigate(TodoScreen.Add.name)
                })
            }
            composable(route = TodoScreen.Add.name){
                EnterTask(
                    navigateUp = {
                        navController.navigateUp()
                    }
                )
            }
        }



    }

}