package com.cryptopulse.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.cryptopulse.presentation.market.MarketRoute


import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cryptopulse.presentation.market.MarketRoute
import com.cryptopulse.presentation.portfolio.AddHoldingScreen
import com.cryptopulse.presentation.portfolio.PortfolioRoute
import com.cryptopulse.presentation.wallet.WalletRoute

@Composable
fun CryptoPulseRoot() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry.value?.destination

    val bottomItems = listOf(
        BottomNavItem.Market,
        BottomNavItem.Portfolio,
        BottomNavItem.Wallet
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomItems.forEach { item ->
                    val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(BottomNavItem.Market.route) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            when (item) {
                                BottomNavItem.Market -> Icon(Icons.Default.Home, contentDescription = item.label)
                                BottomNavItem.Portfolio -> Icon(Icons.Default.AccountBox, contentDescription = item.label)
                                BottomNavItem.Wallet -> Icon(Icons.Default.List, contentDescription = item.label)
                                else -> Unit
                            }
                        },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Market.route,
            modifier = Modifier.padding(padding),
        ) {
            composable(BottomNavItem.Market.route) {
                MarketRoute(
                    onAddHoldingClick = { coinId, symbol ->
                        navController.navigate(BottomNavItem.AddHolding.createRoute(coinId, symbol))
                    }
                )
            }

            composable(BottomNavItem.Portfolio.route) {
                PortfolioRoute()
            }

            composable(BottomNavItem.Wallet.route) {
                WalletRoute()
            }

            composable(BottomNavItem.AddHolding.route) {
                AddHoldingScreen(
                    onSaved = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}