package com.pdmtaller2.t00363823_SamuelAnzora.model
import androidx.annotation.DrawableRes

// Definimos una clase de datos (data class) llamada RestaurantItem
// Representa la información básica de un restaurante que se muestra en la UI
data class RestaurantItem(
    val name: String,
    @DrawableRes val imageRes: Int,
    val category: String
)
