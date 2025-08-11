package com.example.felixmandyme_juniorassessment.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.felixmandyme_juniorassessment.TodoApplication
import com.example.felixmandyme_juniorassessment.data.SunriseSunsetResponse
import com.example.felixmandyme_juniorassessment.data.TemperatureResponse
import com.example.felixmandyme_juniorassessment.data.WeatherRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed interface WeatherUiState{
    data class Success(val weatherInfo: TemperatureResponse, val astronomy: SunriseSunsetResponse): WeatherUiState
    object Error: WeatherUiState
    object Load: WeatherUiState
}

class WeatherViewModel(private val weatherRepository: WeatherRepository): ViewModel() {

    var weatherUiState: WeatherUiState by mutableStateOf(WeatherUiState.Load)
        private set

    init {
        getWeatherTemp()
    }

    fun getWeatherTemp(){
        viewModelScope.launch {
           weatherUiState = WeatherUiState.Load
            weatherUiState = try {
                WeatherUiState.Success(
                    weatherRepository.getWeatherInfo(),
                    weatherRepository.getAstronomyInfo()
                )
            }catch (e: IOException){
                Log.i("api erro" ,"$e")
                WeatherUiState.Error
            }catch (e: HttpException){

                Log.i("api erro" ,"$e")
                WeatherUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
               val application = (this[APPLICATION_KEY] as TodoApplication)
                val weatherRepository = application.container.weatherRepository
                WeatherViewModel(weatherRepository = weatherRepository)
            }
        }
    }

}