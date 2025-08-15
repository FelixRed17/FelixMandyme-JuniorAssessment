package com.example.felixmandyme_juniorassessment.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.felixmandyme_juniorassessment.R

sealed class OnBoardingScreensData(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
){
    object First: OnBoardingScreensData(
        image = R.drawable.page0,
        title = R.string.onBoardingTitleScreenOne,
        description = R.string.onBoardingDescriptionScreenOne
    )

    object Second: OnBoardingScreensData(
        image = R.drawable.page2,
        title = R.string.onBoardingTitleScreenTwo,
        description = R.string.onBoardingDescriptionScreenTwo
    )

    object Third: OnBoardingScreensData(
        image = R.drawable.page3,
        title = R.string.onBoardingTitleScreenThree,
        description = R.string.onBoardingDescriptionScreenThree
    )

}