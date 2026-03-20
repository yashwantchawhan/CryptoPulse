package com.cryptopulse.domain.usecase

import com.cryptopulse.domain.model.Holding
import com.cryptopulse.domain.repository.PortfolioRepository
import javax.inject.Inject

class AddHoldingUseCase @Inject constructor(
    private val repository: PortfolioRepository
) {
    suspend operator fun invoke(holding: Holding) = repository.addHolding(holding)
}