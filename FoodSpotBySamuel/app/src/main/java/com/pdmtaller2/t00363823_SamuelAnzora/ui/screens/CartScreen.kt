import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pdmtaller2.t00363823_SamuelAnzora.R
import com.pdmtaller2.t00363823_SamuelAnzora.model.DishItem

// CartManager is unchanged; it handles cart items and their operations.
object CartManager {
    private val _cartItems = mutableStateListOf<Pair<DishItem, Int>>()
    val cartItems: List<Pair<DishItem, Int>> get() = _cartItems

    fun addToCart(dish: DishItem) {
        val index = _cartItems.indexOfFirst { it.first.name == dish.name }
        if (index >= 0) {
            _cartItems[index] = _cartItems[index].copy(second = _cartItems[index].second + 1)
        } else {
            _cartItems.add(dish to 1)
        }
    }

    fun removeFromCart(dish: DishItem) {
        val index = _cartItems.indexOfFirst { it.first.name == dish.name }
        if (index >= 0) {
            val (item, qty) = _cartItems[index]
            if (qty > 1) {
                _cartItems[index] = item to (qty - 1)
            } else {
                _cartItems.removeAt(index)
            }
        }
    }

    fun clearCart() = _cartItems.clear()
    fun getTotalItems(): Int = _cartItems.sumOf { it.second }
    fun getTotalPrice(): Double = _cartItems.sumOf { (dish, qty) -> dish.price?.times(qty) ?: 0.0 }

    fun formatPrice(value: Double?): String = "$${"%.2f".format(value ?: 0.0)}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController) {
    Scaffold(
        topBar = { CartTopBar(navController) },
        bottomBar = { CartBottomBar(navController) }
    ) { padding ->
        CartContent(padding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CartTopBar(navController: NavController) {
    TopAppBar(
        title = { Text("Mi Carrito (${CartManager.getTotalItems()})") },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
            }
        }
    )
}

@Composable
private fun CartBottomBar(navController: NavController) {
    if (CartManager.cartItems.isNotEmpty()) {
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
                    text = "Total: ${CartManager.formatPrice(CartManager.getTotalPrice())}",
                    style = MaterialTheme.typography.titleLarge
                )
                Button(
                    onClick = {
                        CartManager.clearCart()
                        navController.popBackStack()
                    }
                ) {
                    Text("Pagar")
                }
            }
        }
    }
}

@Composable
private fun CartContent(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        if (CartManager.cartItems.isEmpty()) {
            EmptyCartView()
        } else {
            CartItemsList()
        }
    }
}

@Composable
private fun EmptyCartView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Carrito vacío",
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Tu carrito está vacío",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
private fun CartItemsList() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(CartManager.cartItems, key = { it.first.name }) { (dish, quantity) ->
            CartItemCard(
                dish = dish,
                quantity = quantity,
                onIncrease = { CartManager.addToCart(dish) },
                onDecrease = { CartManager.removeFromCart(dish) }
            )
        }
    }
}

@Composable
private fun CartItemCard(
    dish: DishItem,
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DishImage(dish)
            Spacer(modifier = Modifier.width(16.dp))
            DishInfo(dish, Modifier.weight(1f))
            QuantityControls(quantity, onIncrease, onDecrease)
        }
    }
}

@Composable
private fun DishImage(dish: DishItem) {
    Image(
        painter = painterResource(id = dish.imageRes),
        contentDescription = dish.name,
        modifier = Modifier.size(64.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun DishInfo(dish: DishItem, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = dish.name,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = CartManager.formatPrice(dish.price),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun QuantityControls(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(
            onClick = onDecrease,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Reducir cantidad",
                tint = if (quantity == 1) Color.Gray else MaterialTheme.colorScheme.error
            )
        }

        Text(
            text = quantity.toString(),
            style = MaterialTheme.typography.titleMedium
        )

        IconButton(
            onClick = onIncrease,
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
