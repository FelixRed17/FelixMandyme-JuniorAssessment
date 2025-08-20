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
import com.example.felixmandyme_juniorassessment.ui.screens.onboarding_screen.OnBoardingScreens
import com.example.felixmandyme_juniorassessment.ui.screens.onboarding_screen.StartUpViewModel
import com.example.felixmandyme_juniorassessment.ui.screens.onboarding_screen.components.SplashScreen
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavScreens {

    @Serializable
    data object OnBoardingScreen: NavKey

    @Serializable
    data object TodoScreen: NavKey

}

@Composable
fun NavigationRoot(
    startUpViewModel: StartUpViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val startScreen by startUpViewModel.startScreen.collectAsState()
    if (startScreen == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            SplashScreen()
        }
    } else {
        val backStack = rememberNavBackStack(startScreen!!)
       NavDisplay(
           modifier = modifier,
           backStack = backStack,
           entryDecorators = listOf(
               rememberSavedStateNavEntryDecorator(),
               rememberViewModelStoreNavEntryDecorator(),
               rememberSceneSetupNavEntryDecorator()
           ),
           entryProvider = {key ->
               when(key){
                   is NavScreens.OnBoardingScreen -> {
                       NavEntry(
                           key
                       ){
                           OnBoardingScreens(
                               onEnableClick = {
                                   backStack.add(NavScreens.TodoScreen)
                               }
                           )
                       }
                   }
                   is NavScreens.TodoScreen -> {
                       NavEntry(
                           key
                       ){
                           TodoHome()
                       }
                   }
                    else -> throw RuntimeException("Invalid screen")
               }
           }
       )
    }
}