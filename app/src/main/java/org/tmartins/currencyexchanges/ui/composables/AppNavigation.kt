package org.tmartins.currencyexchanges.ui.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.tmartins.currencyexchanges.ui.NavigationScreen
import org.tmartins.currencyexchanges.ui.conversion.ConversionScreen
import org.tmartins.currencyexchanges.ui.list.RatesList

@Composable
fun AppNavigation(navHostController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationScreen.List.route
    )
    {
        composable(NavigationScreen.List.route) {
            RatesList() {
                navHostController.navigate(NavigationScreen.Conversion.route)
            }
        }

        composable(NavigationScreen.Conversion.route) {
            ConversionScreen()
        }
    }
}