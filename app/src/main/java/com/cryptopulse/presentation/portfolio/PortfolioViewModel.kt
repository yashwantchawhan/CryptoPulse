package com.cryptopulse.presentation.portfolio

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptopulse.domain.model.Holding
import com.cryptopulse.domain.usecase.AddHoldingUseCase
import com.cryptopulse.domain.usecase.GetPortfolioSummaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val getPortfolioSummaryUseCase: GetPortfolioSummaryUseCase,
    private val addHoldingUseCase: AddHoldingUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val selectedCoinId: String = savedStateHandle.get<String>("coinId") ?: ""
    val selectedSymbol: String = savedStateHandle.get<String>("symbol") ?: ""

    private val _uiState = MutableStateFlow(PortfolioUiState())
    val uiState: StateFlow<PortfolioUiState> = _uiState.asStateFlow()

    init {
        observePortfolio()
    }

    private fun observePortfolio() {
        viewModelScope.launch {
            getPortfolioSummaryUseCase()
                .onStart {
                    _uiState.update { it.copy(isLoading = true, error = null) }
                }
                .catch { throwable ->
                    _uiState.update {
                        it.copy(isLoading = false, error = throwable.message ?: "Failed to load portfolio")
                    }
                }
                .collect { items ->
                    _uiState.update {
                        it.copy(isLoading = false, items = items, error = null)
                    }
                }
        }
    }

    fun addHolding(quantity: String, averageBuyPrice: String, onSuccess: () -> Unit) {
        val parsedQuantity = quantity.toDoubleOrNull() ?: return
        val parsedBuyPrice = averageBuyPrice.toDoubleOrNull() ?: return
        if (selectedCoinId.isBlank() || selectedSymbol.isBlank()) return

        viewModelScope.launch {
            addHoldingUseCase(
                Holding(
                    coinId = selectedCoinId,
                    symbol = selectedSymbol,
                    quantity = parsedQuantity,
                    averageBuyPrice = parsedBuyPrice
                )
            )
            onSuccess()
        }
    }
}