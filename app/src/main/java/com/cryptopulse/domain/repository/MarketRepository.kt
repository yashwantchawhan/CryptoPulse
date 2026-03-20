package com.cryptopulse.domain.repository

import com.cryptopulse.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface MarketRepository {
    fun getTopCoins(): Flow<List<Coin>>
}