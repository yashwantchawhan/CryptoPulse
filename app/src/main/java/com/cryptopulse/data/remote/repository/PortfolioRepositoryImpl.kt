package com.cryptopulse.data.remote.repository

import com.cryptopulse.data.local.dao.HoldingDao
import com.cryptopulse.data.mapper.toDomain
import com.cryptopulse.data.mapper.toEntity
import com.cryptopulse.domain.model.Holding
import com.cryptopulse.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PortfolioRepositoryImpl @Inject constructor(
    private val dao: HoldingDao
) : PortfolioRepository {

    override fun getHoldings(): Flow<List<Holding>> {
        return dao.observeHoldings().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun addHolding(holding: Holding) {
        dao.insertHolding(holding.toEntity())
    }
}