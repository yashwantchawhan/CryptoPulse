package com.cryptopulse.domain.usecase

import com.cryptopulse.domain.model.WalletBalance
import com.cryptopulse.domain.repository.WalletRepository
import javax.inject.Inject

class GetWalletBalanceUseCase @Inject constructor(
    private val repository: WalletRepository
) {
    suspend operator fun invoke(address: String): WalletBalance = repository.getWalletBalance(address)
}