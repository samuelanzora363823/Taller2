package com.pdmtaller2.t00363823_SamuelAnzora.model

import androidx.annotation.DrawableRes

data class RestaurantItem(
    val name: String,
    @DrawableRes val imageRes: Int,
    val category: String
)
