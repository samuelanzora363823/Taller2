package com.pdmtaller2.t00363823_SamuelAnzora.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun BottomNavBar(selected: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(24.dp)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        listOf("Listado", "Busqueda", "Mis ordenes").forEach { item ->
            val isSelected = item == selected
            Text(
                text = item,
                modifier = Modifier
                    .padding(8.dp)
                    .background(
                        color = if (isSelected) Color(0xFFA8E6CF) else Color.Transparent,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = if (isSelected) Color.Black else Color.Gray
                )
            )
        }
    }
}
