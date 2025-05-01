package com.pdmtaller2.t00363823_SamuelAnzora.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdmtaller2.t00363823_SamuelAnzora.ui.screens.HomeScreen
import com.pdmtaller2.t00363823_SamuelAnzora.ui.screens.RestaurantMenuScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    onSelectedItemChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "listado",
        modifier = modifier
    ) {
        composable("listado") {
            onSelectedItemChange("Listado")
            HomeScreen(
                onRestaurantClick = { restaurantName ->
                    onSelectedItemChange("Busqueda")
                    navController.navigate("menu/$restaurantName")
                },
                onNavigate = { route ->
                    when (route) {
                        "busqueda" -> onSelectedItemChange("Busqueda")
                        "mis ordenes" -> onSelectedItemChange("Mis ordenes")
                    }
                    navController.navigate(route)
                }
            )
        }

        composable("menu/{restaurantName}") { backStackEntry ->
            onSelectedItemChange("Busqueda")
            val name = backStackEntry.arguments?.getString("restaurantName") ?: ""
            RestaurantMenuScreen(navController = navController, restaurantName = name)
        }
    }
}