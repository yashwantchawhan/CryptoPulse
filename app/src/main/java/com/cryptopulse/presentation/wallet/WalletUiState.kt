package com.cryptopulse.presentation.wallet

import com.cryptopulse.domain.model.Transaction
import com.cryptopulse.domain.model.WalletBalance

data class WalletUiState(
    val isLoading: Boolean = false,
    val walletBalance: WalletBalance? = null,
    val transactions: List<Transaction> = emptyList(),
    val error: String? = null,
    val addressInput: String = ""
)