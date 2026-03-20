package com.cryptopulse.data.remote.repository

import com.cryptopulse.data.remote.api.CoinGeckoApi
import com.cryptopulse.data.remote.dto.toDomain
import com.cryptopulse.domain.model.Coin
import com.cryptopulse.domain.repository.MarketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarketRepositoryImpl @Inject constructor(
    private val api: CoinGeckoApi
) : MarketRepository {

    override fun getTopCoins(): Flow<List<Coin>> = flow {
        val result = api.getTopCoins().map { it.toDomain() }
        emit(result)
    }
}