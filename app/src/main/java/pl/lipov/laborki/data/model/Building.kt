package pl.lipov.laborki.data.model

import androidx.annotation.DrawableRes

data class Building(
    @DrawableRes val iconResId: Int,
    val name: String
)