package com.example.felixmandyme_juniorassessment.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.felixmandyme_juniorassessment.R
import com.example.felixmandyme_juniorassessment.data.AstronomyInfo
import com.example.felixmandyme_juniorassessment.data.WeatherInfo
import com.example.felixmandyme_juniorassessment.ui.viewmodels.WeatherUiState
import com.example.felixmandyme_juniorassessment.ui.viewmodels.WeatherViewModel

@Composable
fun WeatherCard(weatherUiState: WeatherUiState){
    when(weatherUiState){
        is WeatherUiState.Load -> LoadingCard()
        is WeatherUiState.Error -> ErrorCard()
        is WeatherUiState.Success -> SuccessCard(weatherUiState.weatherInfo, weatherUiState.astronomy)
    }
}

@Composable
fun SuccessCard(weatherInfo: WeatherInfo, astronomyInfo: AstronomyInfo){
    Card(modifier = Modifier.fillMaxWidth()){
        Row(modifier = Modifier.fillMaxWidth()) {
            Column {

                Text(text = "${weatherInfo.current.temp} \u00B0C",
                    style = MaterialTheme.typography.headlineLarge
                    )
                Text(
                    text = "Sunset: ${astronomyInfo.astronomy.astro.sunset}"
                )
                Text(
                    text = "Sunrise: ${astronomyInfo.astronomy.astro.sunrise}"
                )

            }

            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.tempicon),
                contentDescription = "Temperature icon",
                modifier = Modifier.size(120.dp),


            )
        }
    }
}

@Composable
fun ErrorCard(){
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_connection_error),
                contentDescription = "Error"
            )
            Text(text = "Error loading weather")
        }
    }
}

@Composable
fun LoadingCard(){
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement =  Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.loading_img),
                contentDescription = "Loading data"
            )
        }
    }
}