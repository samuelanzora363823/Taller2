
package com.pdmtaller2.t00363823_SamuelAnzora.model
import androidx.annotation.DrawableRes

// Definimos una clase de datos (data class) llamada DishItem
data class DishItem(
    // Nombre del platillo (ej. "Big Mac")
    val name: String,

    // Breve descripción del platillo (ej. "Hamburguesa con doble carne")
    val description: String,

    // Referencia al recurso de imagen del platillo
    // La anotación @DrawableRes ayuda al compilador a verificar que este valor es un recurso válido
    @DrawableRes val imageRes: Int,

    // Precio del platillo (opcional, puede ser null)
    val price: Double? = null
)
