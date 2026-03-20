package com.cryptopulse.domain.repository

import com.cryptopulse.domain.model.Holding
import kotlinx.coroutines.flow.Flow

interface PortfolioRepository {
    fun getHoldings(): Flow<List<Holding>>
    suspend fun addHolding(holding: Holding)
}