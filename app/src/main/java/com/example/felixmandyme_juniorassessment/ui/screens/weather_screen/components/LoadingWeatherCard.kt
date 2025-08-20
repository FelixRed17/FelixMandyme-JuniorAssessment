package com.example.felixmandyme_juniorassessment.ui.screens.weather_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.felixmandyme_juniorassessment.R

@Composable
fun LoadingWeatherCard(){
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