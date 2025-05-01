package com.pdmtaller2.t00363823_SamuelAnzora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.pdmtaller2.t00363823_SamuelAnzora.navigation.AppNavGraph
import com.pdmtaller2.t00363823_SamuelAnzora.ui.screens.HomeScreen
import com.pdmtaller2.t00363823_SamuelAnzora.ui.theme.FoodSpotBySamuelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodSpotBySamuelTheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    FoodSpotBySamuelTheme {
        HomeScreen(
            onRestaurantClick = {},
            onNavigate = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
