package com.cryptopulse.domain.model

data class Transaction(
    val hash: String,
    val from: String,
    val to: String,
    val valueEth: Double,
    val timestamp: Long
)