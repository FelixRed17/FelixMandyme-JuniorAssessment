package com.example.felixmandyme_juniorassessment.ui.screens.weather_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.felixmandyme_juniorassessment.domain.model.SunriseSunsetResponse
import com.example.felixmandyme_juniorassessment.domain.model.TemperatureResponse
import com.example.felixmandyme_juniorassessment.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

sealed interface WeatherUiState{
    data class Success(val temperatureResponse: TemperatureResponse, val sunriseSunsetResponse: SunriseSunsetResponse): WeatherUiState
    data object Error: WeatherUiState
    data object Load: WeatherUiState
}

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository): ViewModel() {

    var weatherUiState: WeatherUiState by mutableStateOf(WeatherUiState.Load)
        private set

    init {
        getWeatherTemp()
    }

    private fun getWeatherTemp(){
        viewModelScope.launch {
           weatherUiState = WeatherUiState.Load
            weatherUiState = try {
                WeatherUiState.Success(
                    weatherRepository.getWeatherInfo(),
                    weatherRepository.getAstronomyInfo()
                )
            }catch (e: IOException){
                Log.i("api error" ,"$e")
                WeatherUiState.Error
            }catch (e: HttpException){
                Log.i("api error" ,"$e")
                WeatherUiState.Error
            }
        }
    }
}