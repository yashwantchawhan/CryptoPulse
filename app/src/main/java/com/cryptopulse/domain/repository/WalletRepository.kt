package com.cryptopulse.domain.repository

import com.cryptopulse.domain.model.Transaction
import com.cryptopulse.domain.model.WalletBalance

interface WalletRepository {
    suspend fun getWalletBalance(address: String): WalletBalance
    suspend fun getTransactions(address: String): List<Transaction>
}