package com.cryptopulse.domain.usecase

import com.cryptopulse.domain.model.PortfolioItem
import com.cryptopulse.domain.repository.MarketRepository
import com.cryptopulse.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetPortfolioSummaryUseCase @Inject constructor(
    private val portfolioRepository: PortfolioRepository,
    private val marketRepository: MarketRepository
) {
    operator fun invoke(): Flow<List<PortfolioItem>> {
        return combine(
            portfolioRepository.getHoldings(),
            marketRepository.getTopCoins()
        ) { holdings, coins ->
            holdings.mapNotNull { holding ->
                val coin = coins.find { it.id == holding.coinId } ?: return@mapNotNull null
                val currentValue = holding.quantity * coin.currentPrice
                val investedValue = holding.quantity * holding.averageBuyPrice
                val pnl = currentValue - investedValue
                val pnlPercent = if (investedValue == 0.0) 0.0 else (pnl / investedValue) * 100

                PortfolioItem(
                    holding = holding,
                    currentPrice = coin.currentPrice,
                    currentValue = currentValue,
                    profitLoss = pnl,
                    profitLossPercentage = pnlPercent
                )
            }
        }
    }
}