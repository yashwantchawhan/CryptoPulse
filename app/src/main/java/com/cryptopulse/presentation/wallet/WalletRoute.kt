package com.cryptopulse.presentation.wallet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun WalletRoute(
    viewModel: WalletViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    WalletScreen(
        uiState = uiState,
        onAddressChange = viewModel::onAddressChange,
        onFetchClick = viewModel::fetchWallet
    )
}