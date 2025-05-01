package com.pdmtaller2.t00363823_SamuelAnzora.model

import androidx.annotation.DrawableRes

data class DishItem(
    val name: String,
    val description: String,
    @DrawableRes val imageRes: Int,
    val price: Double? = null
)
