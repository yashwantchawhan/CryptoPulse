package com.cryptopulse.presentation.portfolio


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PortfolioScreen(uiState: PortfolioUiState) {
    when {
        uiState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        uiState.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(uiState.error)
            }
        }

        else -> {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Portfolio Value", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "$${String.format("%.2f", uiState.totalValue)}",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text("PnL: ${String.format("%.2f", uiState.totalProfitLoss)}")
                    }
                }

                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(uiState.items) { item ->
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(item.holding.symbol.uppercase(), fontWeight = FontWeight.Bold)
                                Text("Qty: ${item.holding.quantity}")
                                Text("Avg Buy: $${item.holding.averageBuyPrice}")
                                Text("Current: $${String.format("%.2f", item.currentPrice)}")
                                Text("Value: $${String.format("%.2f", item.currentValue)}")
                                Text("PnL: ${String.format("%.2f", item.profitLoss)} (${String.format("%.2f", item.profitLossPercentage)}%)")
                            }
                        }
                    }
                }
            }
        }
    }
}