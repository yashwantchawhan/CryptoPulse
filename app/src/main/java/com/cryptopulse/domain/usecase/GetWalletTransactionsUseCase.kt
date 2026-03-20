package com.cryptopulse.domain.usecase


import com.cryptopulse.domain.model.Transaction
import com.cryptopulse.domain.repository.WalletRepository
import javax.inject.Inject

class GetWalletTransactionsUseCase @Inject constructor(
    private val repository: WalletRepository
) {
    suspend operator fun invoke(address: String): List<Transaction> = repository.getTransactions(address)
}