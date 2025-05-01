package com.pdmtaller2.t00363823_SamuelAnzora.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdmtaller2.t00363823_SamuelAnzora.ui.screens.*

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "listado") {
        composable("listado") {
            HomeScreen(
                onRestaurantClick = { restaurantName ->
                    navController.navigate("menu/$restaurantName")
                },
                onNavigate = { navController.navigate(it) }
            )
        }
        composable("menu/{restaurantName}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("restaurantName") ?: ""
            RestaurantMenuScreen(navController = navController, restaurantName = name)
        }
    }
}
