package com.example.felixmandyme_juniorassessment.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.felixmandyme_juniorassessment.domain.model.SunriseSunsetResponse
import com.example.felixmandyme_juniorassessment.domain.model.TemperatureResponse

@Composable
fun SuccessWeatherCard(temperatureResponse: TemperatureResponse, sunriseSunsetResponse: SunriseSunsetResponse){
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "🌅️")
                Text(sunriseSunsetResponse.astronomy.astro.sunset)
                Text(text = "Sunset")
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "🌡️", fontSize = 24.sp)
                Text(
                    text = "${temperatureResponse.current.temp} \u00B0C",
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "🌤️")
                Text(sunriseSunsetResponse.astronomy.astro.sunrise)
                Text(text = "Sunrise")
            }
        }
    }
}