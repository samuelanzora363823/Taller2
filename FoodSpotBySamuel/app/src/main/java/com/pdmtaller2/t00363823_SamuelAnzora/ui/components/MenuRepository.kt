package com.pdmtaller2.t00363823_SamuelAnzora.data

import com.pdmtaller2.t00363823_SamuelAnzora.R
import com.pdmtaller2.t00363823_SamuelAnzora.model.DishItem


fun getMenuForRestaurant(name: String): List<DishItem> {
    return when (name) {
        "McDonalds" -> listOf(
            DishItem("Big Mac", "Hamburguesa clásica con doble carne", R.drawable.bigmac),
            DishItem("Papas Fritas", "Papas medianas con sal", R.drawable.papasfritas)
        )

        "Burger King" -> listOf(
            DishItem("Whopper", "Hamburguesa grande con carne a la parrilla", R.drawable.whooper),
            DishItem("Onion Rings", "Aros de cebolla crujientes", R.drawable.onion)
        )

        "KFC" -> listOf(
            DishItem("Bucket Familiar", "10 piezas de pollo frito", R.drawable.bucket),
            DishItem("Alitas BBQ", "Alitas bañadas en salsa BBQ", R.drawable.alitas)
        )

        "Taco Bell" -> listOf(
            DishItem("Crunchy Taco", "Taco crujiente con carne y queso", R.drawable.taco),
            DishItem("Burrito Supreme", "Burrito grande con todo incluido", R.drawable.burrito)
        )

        "Chipotle" -> listOf(
            DishItem("Bowl de Pollo", "Tazón con arroz, pollo, frijoles y guacamole", R.drawable.bolw),
            DishItem("Taco de Carnitas", "Taco suave de carne de cerdo", R.drawable.tcarnitas)
        )

        "El Pollo Loco" -> listOf(
            DishItem("Pollo Asado", "Pollo marinado a la parrilla", R.drawable.azado),
            DishItem("Tacos de Pollo", "Tacos con pollo, repollo y salsa", R.drawable.tpollo)
        )

        else -> emptyList()
    }
}

