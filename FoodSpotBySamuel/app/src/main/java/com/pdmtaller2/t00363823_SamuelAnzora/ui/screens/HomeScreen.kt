package com.pdmtaller2.t00363823_SamuelAnzora.ui.screens

import RestaurantItem
import RestaurantRow
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pdmtaller2.t00363823_SamuelAnzora.R
import com.pdmtaller2.t00363823_SamuelAnzora.ui.components.BottomNavBar


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "FoodSpot",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(text = "Comida Rápida", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        RestaurantRow(
            listOf(
                RestaurantItem("McDonalds", R.drawable.mcdonal),
                RestaurantItem("Burger King", R.drawable.burger),
                RestaurantItem("KFC", R.drawable.kfc)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Comida Mexicana", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        RestaurantRow(
            listOf(
                RestaurantItem("Taco Bell", R.drawable.tacobell),
                RestaurantItem("Chipotle", R.drawable.chipotle),
                RestaurantItem("El Pollo Loco", R.drawable.pollo_loco)
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        BottomNavBar(selected = "Listado")
    }
}
