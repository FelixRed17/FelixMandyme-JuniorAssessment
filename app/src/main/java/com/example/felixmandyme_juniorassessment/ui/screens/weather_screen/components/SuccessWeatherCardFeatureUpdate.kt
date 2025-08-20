package com.example.felixmandyme_juniorassessment.ui.screens.weather_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.felixmandyme_juniorassessment.domain.model.SunriseSunsetResponse
import com.example.felixmandyme_juniorassessment.domain.model.TemperatureResponse
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun SuccessCardFeature(
   temperatureResponse: TemperatureResponse,
   sunsetResponse: SunriseSunsetResponse,
   modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF4FC3F7),
                            Color(0xFF9C27B0)
                        )
                    )
                )
                .padding(24.dp)
        ) {

            Row(
                modifier = Modifier.align(Alignment.TopStart),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Canvas(
                    modifier = Modifier.size(48.dp)
                ) {
                    val center = Offset(size.width / 2, size.height / 2)
                    val radius = size.minDimension / 6

                    // Draw sun rays
                    for (i in 0..7) {
                        val angle = i * 45 * (PI / 180)
                        val startX = center.x + cos(angle).toFloat() * (radius * 2)
                        val startY = center.y + sin(angle).toFloat() * (radius * 2)
                        val endX = center.x + cos(angle).toFloat() * (radius * 2.8f)
                        val endY = center.y + sin(angle).toFloat() * (radius * 2.8f)

                        drawLine(
                            color = Color.White,
                            start = Offset(startX, startY),
                            end = Offset(endX, endY),
                            strokeWidth = 6.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                    }


                    drawCircle(
                        color = Color.White,
                        radius = radius,
                        center = center
                    )
                }

                Text(
                    text = "${temperatureResponse.current.temp}",
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White
                )
            }

            // Sunrise and Sunset info
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Sunrise
                Column {
                    Text(
                        text = "Sunrise",
                        fontSize = 16.sp,
                        color = Color.White.copy(alpha = 0.9f),
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = sunsetResponse.astronomy.astro.sunrise,
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }

                // Sunset
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Sunset",
                        fontSize = 16.sp,
                        color = Color.White.copy(alpha = 0.9f),
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = sunsetResponse.astronomy.astro.sunset,
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

