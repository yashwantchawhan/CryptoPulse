package com.cryptopulse.data.remote.api


import com.cryptopulse.data.remote.dto.EtherscanBalanceResponseDto
import com.cryptopulse.data.remote.dto.EtherscanTransactionResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EtherscanApi {

    @GET(".")
    suspend fun getBalance(
        @Query("module") module: String = "account",
        @Query("action") action: String = "balance",
        @Query("address") address: String,
        @Query("tag") tag: String = "latest",
        @Query("apikey") apiKey: String
    ): EtherscanBalanceResponseDto

    @GET(".")
    suspend fun getTransactions(
        @Query("module") module: String = "account",
        @Query("action") action: String = "txlist",
        @Query("address") address: String,
        @Query("startblock") startBlock: Int = 0,
        @Query("endblock") endBlock: Int = 99999999,
        @Query("page") page: Int = 1,
        @Query("offset") offset: Int = 20,
        @Query("sort") sort: String = "desc",
        @Query("apikey") apiKey: String
    ): EtherscanTransactionResponseDto
}