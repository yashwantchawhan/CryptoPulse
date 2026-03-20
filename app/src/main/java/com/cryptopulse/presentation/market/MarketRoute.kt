package com.cryptopulse.presentation.market

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MarketRoute(
    viewModel: MarketViewModel = hiltViewModel(),
    onAddHoldingClick: (String, String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MarketScreen(
        uiState = uiState,
        onAddHoldingClick = onAddHoldingClick
    )
}