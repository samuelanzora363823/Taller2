package com.pdmtaller2.t00363823_SamuelAnzora.ui.screens

import RestaurantRow
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pdmtaller2.t00363823_SamuelAnzora.data.allRestaurants
import com.pdmtaller2.t00363823_SamuelAnzora.ui.components.BottomNavBar


@Composable
fun HomeScreen(
    onRestaurantClick: (String) -> Unit, // NUEVO
    onNavigate: (String) -> Unit,        // para BottomNavBar, si luego agregas más
    modifier: Modifier = Modifier
) {
    val restaurantsByCategory = allRestaurants.groupBy { it.category }

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

        restaurantsByCategory.forEach { (category, restaurants) ->
            Text(text = category, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            RestaurantRow(restaurants = restaurants, onItemClick = onRestaurantClick) // CAMBIO
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.weight(1f))
        BottomNavBar(selected = "Listado", onNavigate = onNavigate) // CAMBIO
    }
}
