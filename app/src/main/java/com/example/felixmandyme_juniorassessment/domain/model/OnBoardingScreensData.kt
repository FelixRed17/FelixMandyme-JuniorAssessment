package com.example.felixmandyme_juniorassessment.domain.model

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.example.felixmandyme_juniorassessment.R

sealed class OnBoardingScreensData(
    @RawRes val animation: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
){
    object First: OnBoardingScreensData(
        animation = R.raw.welcome,
        title = R.string.onBoardingTitleScreenOne,
        description = R.string.onBoardingDescriptionScreenOne
    )

    object Second: OnBoardingScreensData(
        animation = R.raw.question,
        title = R.string.onBoardingTitleScreenTwo,
        description = R.string.onBoardingDescriptionScreenTwo
    )

    object Third: OnBoardingScreensData(
        animation = R.raw.location,
        title = R.string.onBoardingTitleScreenThree,
        description = R.string.onBoardingDescriptionScreenThree
    )

}