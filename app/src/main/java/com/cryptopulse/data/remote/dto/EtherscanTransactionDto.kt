package com.cryptopulse.data.remote.dto

import com.google.gson.annotations.SerializedName

data class EtherscanTransactionDto(
    @SerializedName("hash") val hash: String,
    @SerializedName("from") val from: String,
    @SerializedName("to") val to: String,
    @SerializedName("value") val value: String,
    @SerializedName("timeStamp") val timeStamp: String
)