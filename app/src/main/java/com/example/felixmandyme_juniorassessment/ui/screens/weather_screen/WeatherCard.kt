package com.example.felixmandyme_juniorassessment.ui.screens.weather_screen

import androidx.compose.runtime.Composable
import com.example.felixmandyme_juniorassessment.ui.screens.weather_screen.components.ErrorWeatherCard
import com.example.felixmandyme_juniorassessment.ui.screens.weather_screen.components.LoadingWeatherCard
import com.example.felixmandyme_juniorassessment.ui.screens.weather_screen.components.SuccessCardFeature

@Composable
fun WeatherCard(weatherUiState: WeatherUiState){
    when(weatherUiState){
        is WeatherUiState.Load -> LoadingWeatherCard()
        is WeatherUiState.Error -> ErrorWeatherCard()
        is WeatherUiState.Success -> SuccessCardFeature(weatherUiState.temperatureResponse, weatherUiState.sunriseSunsetResponse)
    }
}




