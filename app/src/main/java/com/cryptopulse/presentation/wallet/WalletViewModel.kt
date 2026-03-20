package com.cryptopulse.presentation.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptopulse.domain.usecase.GetWalletBalanceUseCase
import com.cryptopulse.domain.usecase.GetWalletTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val getWalletBalanceUseCase: GetWalletBalanceUseCase,
    private val getWalletTransactionsUseCase: GetWalletTransactionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WalletUiState())
    val uiState: StateFlow<WalletUiState> = _uiState.asStateFlow()

    fun onAddressChange(value: String) {
        _uiState.update { it.copy(addressInput = value) }
    }

    fun fetchWallet() {
        val address = uiState.value.addressInput.trim()
        if (address.isBlank()) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val balance = getWalletBalanceUseCase(address)
                val transactions = getWalletTransactionsUseCase(address)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        walletBalance = balance,
                        transactions = transactions,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Failed to fetch wallet"
                    )
                }
            }
        }
    }
}