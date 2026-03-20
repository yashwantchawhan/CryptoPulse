package com.cryptopulse.presentation.market

import com.cryptopulse.domain.model.Coin

data class MarketUiState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String? = null
)