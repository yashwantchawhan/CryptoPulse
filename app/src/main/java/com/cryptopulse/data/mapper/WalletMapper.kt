package com.cryptopulse.data.mapper


import com.cryptopulse.data.remote.dto.EtherscanTransactionDto
import com.cryptopulse.domain.model.Transaction
import java.math.BigDecimal

private val WEI_DIVISOR = "1000000000000000000".toBigDecimal()

fun weiToEth(wei: String): Double {
    return try {
        wei.toBigDecimal().divide(WEI_DIVISOR).toDouble()
    } catch (e: Exception) {
        0.0
    }
}

fun EtherscanTransactionDto.toDomain(): Transaction {
    return Transaction(
        hash = hash,
        from = from,
        to = to,
        valueEth = weiToEth(value),
        timestamp = timeStamp.toLongOrNull() ?: 0L
    )
}