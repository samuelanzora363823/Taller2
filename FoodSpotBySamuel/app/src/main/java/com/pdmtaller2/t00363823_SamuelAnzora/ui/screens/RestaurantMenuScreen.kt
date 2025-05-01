package com.pdmtaller2.t00363823_SamuelAnzora.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pdmtaller2.t00363823_SamuelAnzora.data.getMenuForRestaurant
import com.pdmtaller2.t00363823_SamuelAnzora.model.DishItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantMenuScreen(navController: NavController, restaurantName: String) {
    val context = LocalContext.current
    var search by remember { mutableStateOf(TextFieldValue("")) }
    val dishes = getMenuForRestaurant(restaurantName)
    val filtered = dishes.filter {
        it.name.contains(search.text, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(restaurantName) },
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
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                label = { Text("Buscar platillo") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filtered) { dish ->
                    DishCard(dish = dish) {
                        Toast.makeText(context, "${dish.name} agregado al carrito", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

@Composable
fun DishCard(dish: DishItem, onAdd: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = dish.imageRes),
                contentDescription = dish.name,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = dish.name, style = MaterialTheme.typography.titleMedium)
                Text(text = dish.description, style = MaterialTheme.typography.bodySmall)
            }
            Button(onClick = onAdd) {
                Text("Agregar")
            }
        }
    }
}
