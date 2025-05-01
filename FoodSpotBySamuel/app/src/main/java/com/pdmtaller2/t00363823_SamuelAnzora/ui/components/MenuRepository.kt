package com.pdmtaller2.t00363823_SamuelAnzora.data

import com.pdmtaller2.t00363823_SamuelAnzora.R
import com.pdmtaller2.t00363823_SamuelAnzora.model.DishItem


fun getMenuForRestaurant(name: String): List<DishItem> {
    return when (name) {
        // Comida Rápida
        "McDonalds" -> listOf(
            DishItem("Big Mac", "Hamburguesa clásica con doble carne", R.drawable.bigmac, 5.99),
            DishItem("Papas Fritas", "Papas medianas con sal", R.drawable.papasfritas, 2.49)
        )

        "Burger King" -> listOf(
            DishItem("Whopper", "Hamburguesa grande con carne a la parrilla", R.drawable.whooper, 6.0),
            DishItem("Onion Rings", "Aros de cebolla crujientes", R.drawable.onion, 7.0)
        )

        "KFC" -> listOf(
            DishItem("Bucket Familiar", "10 piezas de pollo frito", R.drawable.bucket, 5.0),
            DishItem("Alitas BBQ", "Alitas bañadas en salsa BBQ", R.drawable.alitas, 2.0)
        )

        // Comida Mexicana
        "Taco Bell" -> listOf(
            DishItem("Crunchy Taco", "Taco crujiente con carne y queso", R.drawable.taco, 4.0),
            DishItem("Burrito Supreme", "Burrito grande con todo incluido", R.drawable.burrito, 8.0)
        )

        "Chipotle" -> listOf(
            DishItem("Bowl de Pollo", "Tazón con arroz, pollo, frijoles y guacamole", R.drawable.bolw, 6.0),
            DishItem("Taco de Carnitas", "Taco suave de carne de cerdo", R.drawable.tcarnitas, 8.0)
        )

        "El Pollo Loco" -> listOf(
            DishItem("Pollo Asado", "Pollo marinado a la parrilla", R.drawable.azado, 7.0),
            DishItem("Tacos de Pollo", "Tacos con pollo, repollo y salsa", R.drawable.tpollo, 6.0)
        )

        // Comida Italiana
        "Olive Garden" -> listOf(
            DishItem("Fettuccine Alfredo", "Pasta con salsa cremosa de queso parmesano", R.drawable.fetuccini, 12.99),
            DishItem("Lasagna", "Lasagna de carne con queso y salsa marinara", R.drawable.lasagna, 14.99)
        )

        "Pizza Hut" -> listOf(
            DishItem("Pizza Suprema", "Pizza con carne, vegetales y salsa especial", R.drawable.pizahut, 16.99),
            DishItem("Alitas de Pollo", "Alitas picantes con salsa barbacoa", R.drawable.alitashut, 8.99)
        )

        "La Trattoria" -> listOf(
            DishItem("Spaghetti Carbonara", "Pasta con salsa de huevo, panceta y queso", R.drawable.spaggeti, 13.50),
            DishItem("Pizza Margherita", "Pizza con tomate, mozzarella y albahaca", R.drawable.margarita, 11.00)
        )

        else -> emptyList()
    }
}
