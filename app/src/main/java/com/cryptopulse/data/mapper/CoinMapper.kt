package com.cryptopulse.data.mapper

import com.cryptopulse.data.remote.dto.CoinMarketDto
import com.cryptopulse.domain.model.Coin

fun CoinMarketDto.toDomain(): Coin {
    return Coin(
        id = id,
        symbol = symbol,
        name = name,
        imageUrl = image,
        currentPrice = currentPrice,
        priceChangePercentage24h = priceChangePercentage24h ?: 0.0
    )
}