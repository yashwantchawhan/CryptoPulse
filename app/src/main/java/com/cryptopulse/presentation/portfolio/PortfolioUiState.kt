package com.cryptopulse.presentation.portfolio

import com.cryptopulse.domain.model.PortfolioItem

data class PortfolioUiState(
    val isLoading: Boolean = false,
    val items: List<PortfolioItem> = emptyList(),
    val error: String? = null
) {
    val totalValue: Double get() = items.sumOf { it.currentValue }
    val totalProfitLoss: Double get() = items.sumOf { it.profitLoss }
}