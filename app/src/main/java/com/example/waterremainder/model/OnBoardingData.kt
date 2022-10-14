package com.example.waterremainder.model

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class OnBoardingData(
    var title: String?,
    var description: String?,
    @RawRes var lottie: Int?
)
