package com.cryptopulse.presentation.portfolio


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AddHoldingScreen(
    viewModel: PortfolioViewModel = hiltViewModel(),
    onSaved: () -> Unit
) {
    val quantity = remember { mutableStateOf("") }
    val averageBuyPrice = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Add ${viewModel.selectedSymbol.uppercase()} Holding",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = quantity.value,
            onValueChange = { quantity.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Quantity") }
        )

        OutlinedTextField(
            value = averageBuyPrice.value,
            onValueChange = { averageBuyPrice.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Average Buy Price") }
        )

        Button(
            onClick = {
                viewModel.addHolding(
                    quantity = quantity.value,
                    averageBuyPrice = averageBuyPrice.value,
                    onSuccess = onSaved
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Holding")
        }
    }
}