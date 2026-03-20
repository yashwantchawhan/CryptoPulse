package com.cryptopulse.presentation.market


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cryptopulse.domain.model.Coin

@Composable
fun MarketScreen(
    uiState: MarketUiState,
    onAddHoldingClick: (String, String) -> Unit
) {
    when {
        uiState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        uiState.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = uiState.error)
            }
        }

        else -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(uiState.coins) { coin ->
                    CoinRow(
                        coin = coin,
                        onClick = { onAddHoldingClick(coin.id, coin.symbol) }
                    )
                }
            }
        }
    }
}

@Composable
private fun CoinRow(
    coin: Coin,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = coin.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(text = coin.symbol.uppercase(), style = MaterialTheme.typography.bodyMedium)
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(text = "$${String.format("%.2f", coin.currentPrice)}")
            Text(text = "${String.format("%.2f", coin.priceChangePercentage24h)}%")
        }
    }
}