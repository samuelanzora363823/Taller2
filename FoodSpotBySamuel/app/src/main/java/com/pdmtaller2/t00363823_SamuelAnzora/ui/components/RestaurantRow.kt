
package com.pdmtaller2.t00363823_SamuelAnzora.ui.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

// Librerías para cargar imágenes de forma eficiente
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

// Importamos nuestro modelo de restaurante
import com.pdmtaller2.t00363823_SamuelAnzora.model.RestaurantItem

// Composable que dibuja una fila horizontal de tarjetas de restaurantes
@Composable
fun RestaurantRow(
    // Lista de restaurantes a mostrar
    restaurants: List<RestaurantItem>,
    // Función a ejecutar cuando se hace clic en un restaurante
    onItemClick: (String) -> Unit,
    // Modificador opcional para personalización externa
    modifier: Modifier = Modifier
) {
    // Contenedor en forma de fila con espacio entre elementos
    Row(
        modifier = modifier
            .fillMaxWidth()        // La fila ocupa todo el ancho disponible
            .height(180.dp)        // Altura fija de 180dp
            .padding(horizontal = 8.dp), // Espacio a los lados
        horizontalArrangement = Arrangement.spacedBy(12.dp) // Espaciado entre tarjetas
    ) {
        // Iteramos sobre cada restaurante para mostrar su tarjeta
        restaurants.forEach { restaurant ->
            // Asignamos una clave única para evitar recomposición innecesaria
            key(restaurant.name) {
                // Mostramos la tarjeta del restaurante
                RestaurantCard(
                    restaurant = restaurant,
                    modifier = Modifier
                        .weight(1f)        // Todas las tarjetas ocupan el mismo ancho
                        .fillMaxHeight(),  // Llenan la altura de la fila
                    onClick = { onItemClick(restaurant.name) } // Acción cuando se hace clic
                )
            }
        }
    }
}

// Composable que representa una tarjeta individual de restaurante
@Composable
fun RestaurantCard(
    // Restaurante que se va a mostrar
    restaurant: RestaurantItem,
    // Modificador para personalizar diseño externo
    modifier: Modifier = Modifier,
    // Acción cuando se hace clic en la tarjeta
    onClick: () -> Unit
) {
    // Tarjeta con bordes redondeados y sombra
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)) // Bordes redondeados
            .clickable(onClick = onClick),   // Hace la tarjeta clickeable
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Elevación (sombra)
    ) {
        // Contenedor para superponer imagen, degradado y texto
        Box(modifier = Modifier.fillMaxSize()) {
            // Cargamos la imagen de forma eficiente con Coil
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(restaurant.imageRes)  // Recurso de la imagen
                    .crossfade(true)            // Transición suave al cargar
                    .build()
            )

            // Mostramos la imagen de fondo en la tarjeta
            Image(
                painter = painter,
                contentDescription = "Imagen de ${restaurant.name}", // Descripción accesible
                contentScale = ContentScale.Crop, // Recorta la imagen para cubrir todo
                modifier = Modifier.fillMaxSize()
            )

            // Capa de fondo degradado en la parte inferior de la imagen
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .align(Alignment.BottomStart) // Posición inferior
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,               // Parte superior transparente
                                Color.Black.copy(alpha = 0.7f)   // Parte inferior oscura
                            ),
                            startY = 0f,
                            endY = 100f
                        )
                    )
            )

            // Columna con el nombre y la categoría del restaurante
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart) // Ubicamos el texto en la parte inferior
                    .padding(12.dp)               // Espaciado interno
            ) {
                // Nombre del restaurante
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    maxLines = 1,                        // Una línea como máximo
                    overflow = TextOverflow.Ellipsis     // Agrega "..." si es muy largo
                )

                // Categoría del restaurante (e.g. Comida Italiana)
                Text(
                    text = restaurant.category,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.8f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
