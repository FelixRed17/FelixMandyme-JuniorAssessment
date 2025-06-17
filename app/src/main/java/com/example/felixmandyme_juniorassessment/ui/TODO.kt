package com.example.felixmandyme_juniorassessment.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.felixmandyme_juniorassessment.ui.screens.TopAppBar
import com.example.felixmandyme_juniorassessment.ui.screens.WeatherCard
import com.example.felixmandyme_juniorassessment.ui.viewmodels.WeatherViewModel


@Composable
fun TodoHome(){
    Scaffold(
        topBar = { TopAppBar() },

    ) { innerpadding ->
        Surface(
            modifier = Modifier
                .padding(innerpadding)
        ) {
            val weatherViewModel: WeatherViewModel = viewModel(
                factory = WeatherViewModel.Factory
            )
            WeatherCard(
                weatherUiState = weatherViewModel.weatherUiState
            )
        }
    }

}