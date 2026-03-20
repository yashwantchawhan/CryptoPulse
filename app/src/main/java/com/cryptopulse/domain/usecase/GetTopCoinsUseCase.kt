package com.cryptopulse.domain.usecase

import com.cryptopulse.domain.model.Coin
import com.cryptopulse.domain.repository.MarketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopCoinsUseCase @Inject constructor(
    private val repository: MarketRepository
) {
    operator fun invoke(): Flow<List<Coin>> = repository.getTopCoins()
}