package com.pdmtaller2.t00363823_SamuelAnzora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pdmtaller2.t00363823_SamuelAnzora.navigation.AppNavGraph
import com.pdmtaller2.t00363823_SamuelAnzora.ui.components.BottomNavBar
import com.pdmtaller2.t00363823_SamuelAnzora.ui.screens.CartManager
import com.pdmtaller2.t00363823_SamuelAnzora.ui.theme.FoodSpotBySamuelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodSpotBySamuelTheme {
                FoodSpotApp()
            }
        }
    }
}

@Composable
fun FoodSpotApp() {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf("Listado") }
    var showBottomBar by remember { mutableStateOf(true) }
    val cartItemCount by remember { derivedStateOf { CartManager.getTotalItems() } }

    // Observar cambios en la navegación
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(navBackStackEntry) {
        when (navBackStackEntry?.destination?.route) {
            "listado" -> {
                selectedItem = "Listado"
                showBottomBar = true
            }
            "busqueda" -> {
                selectedItem = "Busqueda"
                showBottomBar = true
            }
            "carrito" -> {
                selectedItem = "Carrito"
                showBottomBar = true
            }
            else -> showBottomBar = false
        }
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    selected = selectedItem,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            onSelectedItemChange = { selectedItem = it },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FoodSpotBySamuelTheme {
        FoodSpotApp()
    }
}