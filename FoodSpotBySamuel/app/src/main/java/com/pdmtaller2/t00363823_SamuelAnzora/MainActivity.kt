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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.pdmtaller2.t00363823_SamuelAnzora.navigation.AppNavGraph
import com.pdmtaller2.t00363823_SamuelAnzora.ui.components.BottomNavBar
import com.pdmtaller2.t00363823_SamuelAnzora.ui.theme.FoodSpotBySamuelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodSpotBySamuelTheme {
                val navController = rememberNavController()
                var selectedItem by remember { mutableStateOf("Listado") }
                var showBottomBar by remember { mutableStateOf(true) }

                // Observar cambios en la navegación para mostrar/ocultar el BottomBar
                LaunchedEffect(navController) {
                    navController.currentBackStackEntryFlow.collect { backStackEntry ->
                        // Determinar si mostrar u ocultar el BottomBar basado en la ruta actual
                        showBottomBar = when (backStackEntry.destination.route) {
                            "listado" -> true
                            "busqueda" -> true
                            "mis ordenes" -> true
                            else -> false // Oculta el BottomBar en otras pantallas
                        }
                    }
                }

                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            BottomNavBar(
                                selected = selectedItem,
                                onNavigate = { route ->
                                    selectedItem = when (route) {
                                        "listado" -> "Listado"
                                        "busqueda" -> "Busqueda"
                                        "mis ordenes" -> "Mis ordenes"
                                        else -> selectedItem
                                    }
                                    navController.navigate(route) {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
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
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FoodSpotBySamuelTheme {
        val navController = rememberNavController()
        AppNavGraph(navController = navController, onSelectedItemChange = {})
    }
}