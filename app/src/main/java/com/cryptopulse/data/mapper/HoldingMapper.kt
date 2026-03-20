package com.cryptopulse.data.mapper

import com.cryptopulse.data.local.entity.HoldingEntity
import com.cryptopulse.domain.model.Holding

fun HoldingEntity.toDomain(): Holding {
    return Holding(
        coinId = coinId,
        symbol = symbol,
        quantity = quantity,
        averageBuyPrice = averageBuyPrice
    )
}

fun Holding.toEntity(): HoldingEntity {
    return HoldingEntity(
        coinId = coinId,
        symbol = symbol,
        quantity = quantity,
        averageBuyPrice = averageBuyPrice
    )
}