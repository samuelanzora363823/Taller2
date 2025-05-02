
package com.pdmtaller2.t00363823_SamuelAnzora.data
import com.pdmtaller2.t00363823_SamuelAnzora.R
import com.pdmtaller2.t00363823_SamuelAnzora.model.RestaurantItem

// Lista de todos los restaurantes disponibles en la app
val allRestaurants = listOf(
    // ---------- Comida Rápida ----------

    // Restaurante McDonald's
    RestaurantItem("McDonalds", R.drawable.mcdonal, "Comida Rápida"),

    // Restaurante Burger King
    RestaurantItem("Burger King", R.drawable.burger, "Comida Rápida"),

    // Restaurante KFC
    RestaurantItem("KFC", R.drawable.kfc, "Comida Rápida"),

    // ---------- Comida Mexicana ----------

    // Restaurante Taco Bell
    RestaurantItem("Taco Bell", R.drawable.tacobell, "Comida Mexicana"),

    // Restaurante Chipotle
    RestaurantItem("Chipotle", R.drawable.chipotle, "Comida Mexicana"),

    // Restaurante El Pollo Loco
    RestaurantItem("El Pollo Loco", R.drawable.pollo_loco, "Comida Mexicana"),

    // ---------- Comida Italiana ----------

    // Restaurante Olive Garden
    RestaurantItem("Olive Garden", R.drawable.olive, "Comida Italiana"),

    // Restaurante Pizza Hut
    RestaurantItem("Pizza Hut", R.drawable.hut, "Comida Italiana"),

    // Restaurante La Trattoria
    RestaurantItem("La Trattoria", R.drawable.trattoria, "Comida Italiana"),

    // ---------- Comida Asiática ----------

    // Restaurante Panda Express
    RestaurantItem("Panda Express", R.drawable.panda, "Comida Asiática"),

    // Restaurante Sushi Itto
    RestaurantItem("Sushi Itto", R.drawable.sushito, "Comida Asiática"),

    // Restaurante Wok & Roll
    RestaurantItem("Wok & Roll", R.drawable.wokrool, "Comida Asiática")
)
