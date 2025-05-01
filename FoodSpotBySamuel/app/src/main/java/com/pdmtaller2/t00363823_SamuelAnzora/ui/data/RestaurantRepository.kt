package com.pdmtaller2.t00363823_SamuelAnzora.data

import com.pdmtaller2.t00363823_SamuelAnzora.R
import com.pdmtaller2.t00363823_SamuelAnzora.model.RestaurantItem

val allRestaurants = listOf(
    // Comida Rápida
    RestaurantItem("McDonalds", R.drawable.mcdonal, "Comida Rápida"),
    RestaurantItem("Burger King", R.drawable.burger, "Comida Rápida"),
    RestaurantItem("KFC", R.drawable.kfc, "Comida Rápida"),

    // Comida Mexicana
    RestaurantItem("Taco Bell", R.drawable.tacobell, "Comida Mexicana"),
    RestaurantItem("Chipotle", R.drawable.chipotle, "Comida Mexicana"),
    RestaurantItem("El Pollo Loco", R.drawable.pollo_loco, "Comida Mexicana"),

    // Comida Italiana
    RestaurantItem("Olive Garden", R.drawable.olive, "Comida Italiana"),
    RestaurantItem("Pizza Hut", R.drawable.hut, "Comida Italiana"),
    RestaurantItem("La Trattoria", R.drawable.trattoria, "Comida Italiana")
)
