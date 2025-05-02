package com.pdmtaller2.t00363823_SamuelAnzora.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

// Composable que representa una barra de navegación inferior personalizada
@Composable
fun BottomNavBar(
    selected: String, // Ruta o nombre de la pestaña actualmente seleccionada
    onNavigate: (String) -> Unit, // Función que se ejecuta al navegar a otra sección
    modifier: Modifier = Modifier, // Modificador externo para personalizar desde fuera
    isInDetailScreen: Boolean = false // Desactiva navegación si estás viendo detalles
) {
    // Obtiene los espacios de la barra del sistema (navegación, status bar, etc.)
    val systemBarInsets = WindowInsets.systemBars.asPaddingValues()

    // Calcula el padding inferior necesario por la barra de navegación
    val navigationBarHeight = systemBarInsets.calculateBottomPadding()

    // Contenedor vertical para posicionar la barra
    Column(
        modifier = Modifier.fillMaxWidth() // Ocupa todo el ancho
    ) {
        // Contenedor horizontal con los ítems del navbar
        Row(
            modifier = modifier
                .fillMaxWidth() // Ocupa todo el ancho
                .background(MaterialTheme.colorScheme.surface) // Color de fondo
                .padding(
                    top = 8.dp,
                    bottom = 8.dp + navigationBarHeight // Añade espacio inferior
                )
                .height(72.dp), // Altura fija del navbar
            horizontalArrangement = Arrangement.SpaceAround, // Espaciado equitativo
            verticalAlignment = Alignment.CenterVertically // Centrado vertical
        ) {
            // Ítem de navegación: Listado
            NavItem(
                icon = Icons.Filled.List, // Icono de lista
                label = "Listado", // Texto que aparece debajo del icono
                isSelected = selected == "Listado", // Marca como seleccionado si coincide
                onClick = { onNavigate("listado") }, // Llama a la navegación al hacer clic
                enabled = !isInDetailScreen // Deshabilita si estás en una pantalla de detalle
            )

            // Ítem de navegación: Búsqueda
            NavItem(
                icon = Icons.Filled.Search,
                label = "Busqueda",
                isSelected = selected == "Busqueda",
                onClick = { onNavigate("busqueda") },
                enabled = !isInDetailScreen
            )

            // Ítem de navegación: Carrito, con badge si hay productos
            NavItem(
                icon = Icons.Filled.ShoppingCart,
                label = "Carrito",
                isSelected = selected == "Carrito",
                onClick = { onNavigate("carrito") },
                enabled = !isInDetailScreen,
                badgeCount = CartManager.getTotalItems() // Muestra cuántos ítems hay en el carrito
            )
        }
    }
}

// Función auxiliar para crear un botón del navbar (ícono + texto)
@Composable
private fun NavItem(
    icon: ImageVector, // Icono que se muestra
    label: String, // Texto debajo del icono
    isSelected: Boolean, // Si está seleccionado (resaltado)
    onClick: () -> Unit, // Acción al hacer clic
    enabled: Boolean, // Si está habilitado
    badgeCount: Int = 0 // Número opcional para mostrar badge (por defecto 0)
) {
    // Determina el color del contenido según si está seleccionado
    val contentColor = if (isSelected) {
        MaterialTheme.colorScheme.primary // Color principal si está seleccionado
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) // Color tenue si no lo está
    }

    // Contenedor principal del ítem (ícono + texto)
    Box(
        modifier = Modifier
            .clickable(enabled = enabled, onClick = onClick) // Solo clickeable si está habilitado
            .padding(horizontal = 12.dp, vertical = 8.dp), // Espaciado interno
        contentAlignment = Alignment.Center // Centrado del contenido
    ) {
        // Columna para alinear ícono y texto uno debajo del otro
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Box con badge (si badgeCount > 0)
            BadgedBox(
                badge = {
                    if (badgeCount > 0) { // Solo muestra el badge si hay ítems
                        Badge {
                            Text(text = badgeCount.toString()) // Número en el badge
                        }
                    }
                }
            ) {
                // Ícono principal del ítem
                Icon(
                    imageVector = icon,
                    contentDescription = label, // Accesibilidad
                    modifier = Modifier.size(28.dp),
                    tint = contentColor // Color determinado por si está seleccionado
                )
            }

            // Espaciado entre ícono y texto
            Spacer(modifier = Modifier.height(4.dp))

            // Texto debajo del ícono
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall, // Estilo pequeño
                color = contentColor,
                maxLines = 1, // Evita que se desborde
                overflow = TextOverflow.Ellipsis // Si es muy largo, lo corta con "..."
            )
        }
    }
}
