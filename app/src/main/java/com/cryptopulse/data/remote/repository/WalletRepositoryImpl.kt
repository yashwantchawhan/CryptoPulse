package com.cryptopulse.data.remote.repository

import com.cryptopulse.data.mapper.toDomain
import com.cryptopulse.data.mapper.weiToEth
import com.cryptopulse.data.remote.api.EtherscanApi
import com.cryptopulse.domain.model.Transaction
import com.cryptopulse.domain.model.WalletBalance
import com.cryptopulse.domain.repository.WalletRepository
import com.cryptopulse.utils.Constants
import javax.inject.Inject

class WalletRepositoryImpl @Inject constructor(
    private val api: EtherscanApi
) : WalletRepository {

    override suspend fun getWalletBalance(address: String): WalletBalance {
        val response = api.getBalance(address = address, apiKey = Constants.ETHERSCAN_API_KEY)
        return WalletBalance(
            address = address,
            balanceEth = weiToEth(response.result)
        )
    }

    override suspend fun getTransactions(address: String): List<Transaction> {
        return api.getTransactions(address = address, apiKey = Constants.ETHERSCAN_API_KEY)
            .result
            .map { it.toDomain() }
    }
}