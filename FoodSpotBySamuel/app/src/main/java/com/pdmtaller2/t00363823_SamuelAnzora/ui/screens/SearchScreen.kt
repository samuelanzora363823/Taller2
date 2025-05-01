package com.pdmtaller2.t00363823_SamuelAnzora.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pdmtaller2.t00363823_SamuelAnzora.data.allRestaurants
import com.pdmtaller2.t00363823_SamuelAnzora.data.getMenuForRestaurant
import com.pdmtaller2.t00363823_SamuelAnzora.model.DishItem
import com.pdmtaller2.t00363823_SamuelAnzora.model.RestaurantItem





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    // Normalizamos la cadena de búsqueda eliminando todos los espacios y convirtiéndolo a minúsculas
    val normalizedSearchQuery = searchQuery.text.replace(Regex("\\s+"), "").lowercase()

    // Aquí creamos una lista con todos los platos de todos los restaurantes
    val allDishes = allRestaurants.flatMap { restaurant ->
        getMenuForRestaurant(restaurant.name).map { dish ->
            Pair(restaurant, dish)
        }
    }

    // Filtramos los restaurantes y platos con la normalización aplicada
    val filteredRestaurants = allRestaurants.filter {
        it.name.replace(Regex("\\s+"), "").lowercase().contains(normalizedSearchQuery) ||
                it.category.replace(Regex("\\s+"), "").lowercase().contains(normalizedSearchQuery)
    }

    val filteredDishes = allDishes.filter {
        it.second.name.replace(Regex("\\s+"), "").lowercase().contains(normalizedSearchQuery) ||
                it.second.description?.replace(Regex("\\s+"), "")?.lowercase()?.contains(normalizedSearchQuery) == true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buscar") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar restaurantes o platillos") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray
                )
            )

            if (searchQuery.text.isNotEmpty()) {
                if (filteredRestaurants.isNotEmpty()) {
                    Text(
                        text = "Restaurantes",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(filteredRestaurants) { restaurant ->
                            RestaurantSearchItem(restaurant, navController)
                        }
                    }
                }

                if (filteredDishes.isNotEmpty()) {
                    Text(
                        text = "Platillos",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(filteredDishes) { (restaurant, dish) ->
                            DishSearchItem(dish, restaurant, navController)
                        }
                    }
                }

                if (filteredRestaurants.isEmpty() && filteredDishes.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No se encontraron resultados")
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Busca restaurantes o platillos")
                }
            }
        }
    }
}

@Composable
fun RestaurantSearchItem(restaurant: RestaurantItem, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("menu/${restaurant.name}")
            }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = restaurant.imageRes),
                contentDescription = restaurant.name,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = restaurant.category,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun DishSearchItem(dish: DishItem, restaurant: RestaurantItem, navController: NavController) {
    var isAdded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .clickable { /* Opcional: navegación a detalles */ },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = dish.imageRes),
                contentDescription = dish.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = dish.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = dish.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                dish.price?.let {
                    Text(
                        text = "$${"%.2f".format(it)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Button(
                onClick = {
                    CartManager.addToCart(dish)
                    isAdded = true
                },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(100.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isAdded) MaterialTheme.colorScheme.tertiary
                    else MaterialTheme.colorScheme.primary
                )
            ) {
                if (isAdded) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Agregado",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("✓")
                } else {
                    Text("Agregar")
                }
            }
        }
    }
}

@Composable
fun SearchScreenPreview() {
    val navController = rememberNavController()
    SearchScreen(navController = navController)
}
