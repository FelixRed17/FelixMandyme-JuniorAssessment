package com.example.felixmandyme_juniorassessment.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.felixmandyme_juniorassessment.ui.TodoHome
import com.example.felixmandyme_juniorassessment.ui.screens.OnBoardingScreens
import com.example.felixmandyme_juniorassessment.ui.screens.SplashScreen
import com.example.felixmandyme_juniorassessment.ui.viewmodels.StartUpViewModel
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavScreens{

    @Serializable
    data object onBoardingPager: NavKey

    @Serializable
    data object todoScreen: NavKey
}

@Composable
fun NavigationRoot(
    startUpViewModel: StartUpViewModel = hiltViewModel(),
) {
    val startScreen by startUpViewModel.startScreen.collectAsState()
    if (startScreen == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            SplashScreen()
        }
    } else {
        val backStack = rememberNavBackStack(startScreen!!)

        NavDisplay(
            backStack = backStack,
            entryDecorators = listOf(
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
                rememberSceneSetupNavEntryDecorator()
            ),
            entryProvider = { key ->
                when (key) {
                    is NavScreens.onBoardingPager -> {
                        NavEntry(key = key) {
                            OnBoardingScreens(
                                onEnableLocationClick = {
                                    backStack.add(NavScreens.todoScreen)
                                }
                            )
                        }
                    }
                    is NavScreens.todoScreen -> {
                        NavEntry(key = key) {
                            TodoHome()
                        }
                    }
                    else -> throw RuntimeException("Invalid screen key")
                }
            }
        )
    }
}
