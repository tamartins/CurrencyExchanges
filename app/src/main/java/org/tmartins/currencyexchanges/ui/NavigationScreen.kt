package org.tmartins.currencyexchanges.ui

sealed class NavigationScreen(val route: String) {
    data object List : NavigationScreen("list")
}