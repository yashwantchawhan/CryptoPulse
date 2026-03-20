package com.cryptopulse.presentation.navigation

sealed class BottomNavItem(val route: String, val label: String) {
    data object Market : BottomNavItem("market", "Market")
    data object Portfolio : BottomNavItem("portfolio", "Portfolio")
    data object Wallet : BottomNavItem("wallet", "Wallet")
    data object AddHolding : BottomNavItem("add_holding/{coinId}/{symbol}", "Add Holding") {
        fun createRoute(coinId: String, symbol: String): String = "add_holding/$coinId/$symbol"
    }
}