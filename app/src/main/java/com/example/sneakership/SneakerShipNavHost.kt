package com.example.sneakership

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sneakership.features.home.composables.HomeScreen
import com.example.sneakership.features.sneakerDetails.composables.SneakerDetailScreen
import com.google.gson.Gson

@Composable
fun SneakerShipNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "home"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home") {
            HomeScreen {
                val sneaker = Gson().toJson(it)
                navController.navigate("sneakerDetails/$sneaker")
            }
        }

        composable("sneakerDetails/{sneaker}", arguments = listOf(
            navArgument("sneaker") {
                type = NavType.StringType
            }
        )) {
            SneakerDetailScreen {
                navController.popBackStack()
            }
        }

        composable("cart") {

        }
    }
}