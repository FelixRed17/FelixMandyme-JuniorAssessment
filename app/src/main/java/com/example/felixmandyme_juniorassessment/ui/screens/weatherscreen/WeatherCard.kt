package com.example.felixmandyme_juniorassessment.ui.screens.weatherscreen

import androidx.compose.runtime.Composable

@Composable
fun WeatherCard(weatherUiState: WeatherUiState){
    when(weatherUiState){
        is WeatherUiState.Load -> LoadingWeatherCard()
        is WeatherUiState.Error -> ErrorWeatherCard()
        is WeatherUiState.Success -> SuccessWeatherCard(weatherUiState.weatherInfo, weatherUiState.astronomy)
    }
}




