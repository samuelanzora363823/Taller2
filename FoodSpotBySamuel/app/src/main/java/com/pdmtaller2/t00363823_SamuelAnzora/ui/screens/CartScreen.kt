package com.pdmtaller2.t00363823_SamuelAnzora.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.pdmtaller2.t00363823_SamuelAnzora.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pdmtaller2.t00363823_SamuelAnzora.model.DishItem

// Objeto global para almacenar el carrito
object CartManager {
    val cartItems = mutableStateListOf<Pair<DishItem, Int>>() // Pair<DishItem, Quantity>

    fun addToCart(dish: DishItem) {
        val existingItem = cartItems.find { it.first.name == dish.name }
        if (existingItem != null) {
            val index = cartItems.indexOf(existingItem)
            cartItems[index] = existingItem.copy(second = existingItem.second + 1)
        } else {
            cartItems.add(Pair(dish, 1))
        }
    }

    fun removeFromCart(dish: DishItem) {
        val existingItem = cartItems.find { it.first.name == dish.name }
        if (existingItem != null) {
            if (existingItem.second > 1) {
                val index = cartItems.indexOf(existingItem)
                cartItems[index] = existingItem.copy(second = existingItem.second - 1)
            } else {
                cartItems.remove(existingItem)
            }
        }
    }

    fun getTotalItems(): Int = cartItems.sumOf { it.second }

    fun getTotalPrice(): Double = cartItems.sumOf { it.first.price?.times(it.second) ?: 0.0 }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController) {
    val cartItems by remember { mutableStateOf(CartManager.cartItems) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Carrito (${CartManager.getTotalItems()})") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                tonalElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total: $${String.format("%.2f", CartManager.getTotalPrice())}",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Button(
                        onClick = { /* Procesar pedido */ },
                        enabled = cartItems.isNotEmpty()
                    ) {
                        Text("Pagar")
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            if (cartItems.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Tu carrito está vacío")
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(cartItems.toList(), key = { it.first.name }) { (dish, quantity) ->
                        CartItem(dish, quantity, onQuantityChange = { newQuantity ->
                            if (newQuantity > quantity) {
                                CartManager.addToCart(dish)
                            } else {
                                CartManager.removeFromCart(dish)
                            }
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun CartItem(
    dish: DishItem,
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = dish.imageRes),
                contentDescription = dish.name,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = dish.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = dish.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "$${dish.price ?: "0.00"}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = { onQuantityChange(quantity - 1) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Reducir cantidad",
                        tint = if (quantity == 1) Color.Gray else Color.Red
                    )
                }

                Text(
                    text = quantity.toString(),
                    style = MaterialTheme.typography.titleMedium
                )

                IconButton(
                    onClick = { onQuantityChange(quantity + 1) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_input_add),
                        contentDescription = "Aumentar cantidad",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    // Agregar algunos items al carrito para la vista previa
    CartManager.cartItems.clear()
    CartManager.cartItems.addAll(listOf(
        Pair(DishItem("Big Mac", "Hamburguesa clásica", R.drawable.bigmac, 5.99), 2),
        Pair(DishItem("Papas Fritas", "Papas medianas", R.drawable.papasfritas, 2.49), 1)
    ))

    val navController = rememberNavController()
    CartScreen(navController)
}