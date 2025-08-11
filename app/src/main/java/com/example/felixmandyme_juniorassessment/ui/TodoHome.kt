package com.example.felixmandyme_juniorassessment.ui

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.felixmandyme_juniorassessment.ui.screens.MainScreen
import com.example.felixmandyme_juniorassessment.ui.screens.TopAppBar

@Composable
fun TodoHome(){
    Scaffold(
        topBar = { TopAppBar() }
    ){ innerpadding ->
        Surface (
            modifier = Modifier
                .padding(
                    start = innerpadding.calculateStartPadding(LayoutDirection.Ltr),
                    end = innerpadding.calculateEndPadding(LayoutDirection.Ltr),
                    bottom = innerpadding.calculateBottomPadding(),
                    top= 60.dp
                )
                .fillMaxSize()
        ){
            MainScreen()
        }
    }

}