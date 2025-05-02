
package com.pdmtaller2.t00363823_SamuelAnzora.navigation
import CartScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdmtaller2.t00363823_SamuelAnzora.ui.screens.HomeScreen
import com.pdmtaller2.t00363823_SamuelAnzora.ui.screens.RestaurantMenuScreen
import com.pdmtaller2.t00363823_SamuelAnzora.ui.screens.SearchScreen

// Definición privada de las rutas que usará la app para navegar
private object Routes {
    const val LISTADO = "listado"               // Ruta principal (pantalla de inicio o lista de restaurantes)
    const val BUSQUEDA = "busqueda"             // Pantalla de búsqueda
    const val CARRITO = "carrito"               // Pantalla del carrito
    const val MENU = "menu/{restaurantName}"    // Pantalla de menú, con un parámetro dinámico
}

// Función composable que define el grafo de navegación de toda la app
@Composable
fun AppNavGraph(
    navController: NavHostController,                 // Controlador de navegación
    onSelectedItemChange: (String) -> Unit,           // Callback para actualizar el ítem seleccionado en el BottomNavigation
    modifier: Modifier = Modifier                     // Modificador opcional
) {
    NavHost(
        navController = navController,
        startDestination = Routes.LISTADO,            // Pantalla inicial al iniciar la app
        modifier = modifier
    ) {
        // Pantalla de listado de restaurantes
        composable(Routes.LISTADO) {
            onSelectedItemChange("Listado")
            HomeScreen(
                onRestaurantClick = { restaurantName ->
                    // Navega al menú del restaurante seleccionado
                    navController.navigate("menu/$restaurantName")
                },
                onNavigate = { route ->
                    // Navega a cualquier otra ruta sin duplicarla en el backstack
                    navController.navigate(route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        // Pantalla de menú de un restaurante específico (usando el nombre como parámetro)
        composable(Routes.MENU) { backStackEntry ->
            // Extrae el nombre del restaurante desde los argumentos de la ruta
            val restaurantName = backStackEntry.arguments?.getString("restaurantName").orEmpty()
            RestaurantMenuScreen(navController = navController, restaurantName = restaurantName)
        }

        // Pantalla de búsqueda
        composable(Routes.BUSQUEDA) {
            onSelectedItemChange("Busqueda")
            SearchScreen(navController = navController)
        }

        // Pantalla del carrito
        composable(Routes.CARRITO) {
            onSelectedItemChange("Carrito")
            CartScreen(navController = navController)
        }
    }
}
