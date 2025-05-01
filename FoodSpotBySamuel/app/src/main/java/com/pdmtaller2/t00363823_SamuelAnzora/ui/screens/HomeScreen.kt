package com.pdmtaller2.t00363823_SamuelAnzora.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pdmtaller2.t00363823_SamuelAnzora.data.allRestaurants
import com.pdmtaller2.t00363823_SamuelAnzora.ui.components.BottomNavBar
import com.pdmtaller2.t00363823_SamuelAnzora.ui.components.RestaurantRow


@Composable
fun HomeScreen(
    onRestaurantClick: (String) -> Unit,
    onNavigate: (String) -> Unit,
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
            RestaurantRow(restaurants = restaurants, onItemClick = onRestaurantClick)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onRestaurantClick = {},
        onNavigate = {},
        modifier = Modifier.fillMaxSize()
    )
}