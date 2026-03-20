package com.cryptopulse.presentation.wallet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WalletScreen(
    uiState: WalletUiState,
    onAddressChange: (String) -> Unit,
    onFetchClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = uiState.addressInput,
            onValueChange = onAddressChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Ethereum Address") }
        )

        Button(
            onClick = onFetchClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Fetch Wallet")
        }

        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        uiState.error?.let {
            Text(it)
        }

        uiState.walletBalance?.let { balance ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Address", style = MaterialTheme.typography.titleMedium)
                    Text(balance.address)
                    Text("Balance: ${String.format("%.6f", balance.balanceEth)} ETH")
                }
            }
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(uiState.transactions) { tx ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Hash: ${tx.hash.take(12)}...")
                        Text("From: ${tx.from.take(12)}...")
                        Text("To: ${tx.to.take(12)}...")
                        Text("Value: ${String.format("%.6f", tx.valueEth)} ETH")
                        Text("Time: ${formatTimestamp(tx.timestamp)}")
                    }
                }
            }
        }
    }
}


private fun formatTimestamp(timestamp: Long): String {
    if (timestamp == 0L) return "Unknown"
    val format = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
    return format.format(Date(timestamp * 1000))
}
