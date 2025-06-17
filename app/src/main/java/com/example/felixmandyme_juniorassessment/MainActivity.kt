package com.example.felixmandyme_juniorassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.felixmandyme_juniorassessment.ui.TodoHome
import com.example.felixmandyme_juniorassessment.ui.screens.WeatherCard
import com.example.felixmandyme_juniorassessment.ui.theme.FelixMandymeJuniorAssessmentTheme
import com.example.felixmandyme_juniorassessment.ui.viewmodels.WeatherViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FelixMandymeJuniorAssessmentTheme {
                TodoHome()
            }
        }
    }
}

