package com.cryptopulse.domain.model

data class PortfolioItem(
    val holding: Holding,
    val currentPrice: Double,
    val currentValue: Double,
    val profitLoss: Double,
    val profitLossPercentage: Double
)