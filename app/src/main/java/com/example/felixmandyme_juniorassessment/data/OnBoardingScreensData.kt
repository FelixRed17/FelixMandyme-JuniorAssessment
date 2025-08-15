package com.example.felixmandyme_juniorassessment.data

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.example.felixmandyme_juniorassessment.R

sealed class OnBoardingScreensData(
    @RawRes val image: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
){
    object First: OnBoardingScreensData(
        image = R.raw.welcome,
        title = R.string.onBoardingTitleScreenOne,
        description = R.string.onBoardingDescriptionScreenOne
    )

    object Second: OnBoardingScreensData(
        image = R.raw.question,
        title = R.string.onBoardingTitleScreenTwo,
        description = R.string.onBoardingDescriptionScreenTwo
    )

    object Third: OnBoardingScreensData(
        image = R.raw.location,
        title = R.string.onBoardingTitleScreenThree,
        description = R.string.onBoardingDescriptionScreenThree
    )

}