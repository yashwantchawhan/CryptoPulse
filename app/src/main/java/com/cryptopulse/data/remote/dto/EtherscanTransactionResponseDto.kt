package com.cryptopulse.data.remote.dto

data class EtherscanTransactionResponseDto(
    val status: String,
    val message: String,
    val result: List<EtherscanTransactionDto>
)