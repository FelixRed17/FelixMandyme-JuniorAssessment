package com.example.felixmandyme_juniorassessment.ui.screens

import androidx.compose.runtime.Composable
import com.example.felixmandyme_juniorassessment.ui.viewmodels.WeatherUiState

@Composable
fun WeatherCard(weatherUiState: WeatherUiState){
    when(weatherUiState){
        is WeatherUiState.Load -> LoadingWeatherCard()
        is WeatherUiState.Error -> ErrorWeatherCard()
        is WeatherUiState.Success -> SuccessWeatherCard(weatherUiState.weatherInfo, weatherUiState.astronomy)
    }
}




