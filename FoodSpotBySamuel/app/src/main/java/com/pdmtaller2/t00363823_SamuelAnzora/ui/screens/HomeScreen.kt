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


import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun HomeScreen(
    onRestaurantClick: (String) -> Unit, // Callback al hacer clic en un restaurante
    onNavigate: (String) -> Unit,        // Callback para navegación general
    modifier: Modifier = Modifier
) {
    // Agrupa todos los restaurantes por categoría
    val restaurantsByCategory = allRestaurants.groupBy { it.category }

    // Estado de scroll para permitir desplazamiento vertical
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()                     // Ocupa todo el tamaño disponible
            .verticalScroll(scrollState)       // Habilita scroll vertical
            .padding(16.dp)                    // Margen interno uniforme
    ) {
        // Título principal de la pantalla
        Text(
            text = "FoodSpot",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Por cada categoría, muestra su nombre y los restaurantes correspondientes
        restaurantsByCategory.forEach { (category, restaurants) ->
            Text(text = category, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre título y contenido
            RestaurantRow(restaurants = restaurants, onItemClick = onRestaurantClick)
            Spacer(modifier = Modifier.height(16.dp)) // Espacio entre categorías
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