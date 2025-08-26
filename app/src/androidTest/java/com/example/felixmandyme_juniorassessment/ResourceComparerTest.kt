package com.example.felixmandyme_juniorassessment

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class ResourceComparerParameterizedTest(
    private val resId: Int,
    private val expected: String,
    private val wrong: String
) {
    private lateinit var resourceComparer: ResourceComparer
    private lateinit var context: Context

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "resId={0}, expected={1}")
        fun data(): List<Array<Any>> {
            return listOf(
                arrayOf(R.string.app_name, "FelixMandyme-JuniorAssessment", "FelixMandyme"),
                arrayOf(R.string.city, "Sandton city", "Cape Town"),
                arrayOf(R.string.text_box_title, "Task title", "Task Name"),
                arrayOf(R.string.addScreen, "Add Todo", "Add Task"),
                arrayOf(R.string.TodoApp, "Todo", "Todo App"),
                arrayOf(R.string.save_task, "Save Task", "Save"),
                arrayOf(R.string.no_task, "No task, Tap + below to add task", "No task yet"),
                arrayOf(R.string.no_completed_task, "No completed task", "Nothing done"),
                arrayOf(R.string.alertDialogTitle, "Completed Task", "Task Done"),
                arrayOf(R.string.alertDialogDescription, "What would you like to do with this completed task?", "Choose task action"),
                arrayOf(R.string.alertDialogCancel, "Cancel", "Close"),
                arrayOf(R.string.alertDialogDelete, "Delete", "Remove"),
                arrayOf(R.string.alertDialogMarkIncomplete, "Mark Incomplete", "Undo"),
                arrayOf(R.string.title, "Title", "Heading"),
                arrayOf(R.string.description, "Description", "Details"),
                arrayOf(R.string.apiError, "Error loading weather", "Weather failed"),
                arrayOf(R.string.onBoardingTitleScreenOne, "Welcome to TaskWeather", "Welcome App"),
                arrayOf(R.string.onBoardingDescriptionScreenOne, "Plan your day with ease. Track your tasks and get weather updates tailored to your location.", "Wrong text"),
                arrayOf(R.string.onBoardingTitleScreenTwo, "Why we need your location", "Location Info"),
                arrayOf(R.string.onBoardingDescriptionScreenTwo, "Your location lets us give you accurate temperature, sunrise, and sunset times, so you can plan tasks at the best time of day.", "Wrong text"),
                arrayOf(R.string.onBoardingTitleScreenThree, "Enable Location Access", "Location Access"),
                arrayOf(R.string.onBoardingDescriptionScreenThree, "We only use your location to give you the most relevant weather insights. Allow access to get started with smarter task planning.", "Wrong text"),
                arrayOf(R.string.enableLocationButton, "Enable Location", "Allow Location")
            )
        }
    }

    @Before
    fun setUp() {
        resourceComparer = ResourceComparer()
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun stringResource_correctString_returnsTrue() {
        val result = resourceComparer.isEqual(context, resId, expected)
        assertThat(result).isTrue()
    }

    @Test
    fun stringResource_incorrectString_returnsFalse() {
        val result = resourceComparer.isEqual(context, resId, wrong)
        assertThat(result).isFalse()
    }
}
