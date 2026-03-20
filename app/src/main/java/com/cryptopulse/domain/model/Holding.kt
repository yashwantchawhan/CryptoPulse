package com.cryptopulse.domain.model

data class Holding(
    val coinId: String,
    val symbol: String,
    val quantity: Double,
    val averageBuyPrice: Double
)