package com.example.felixmandyme_juniorassessment

import android.app.Application
import com.example.felixmandyme_juniorassessment.data.AppContainer
import com.example.felixmandyme_juniorassessment.data.DefaultAppContainer

class TodoApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}