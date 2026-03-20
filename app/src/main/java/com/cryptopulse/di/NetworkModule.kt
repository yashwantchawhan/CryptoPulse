package com.cryptopulse.di


import com.cryptopulse.data.remote.api.CoinGeckoApi
import com.cryptopulse.data.remote.api.EtherscanApi
import com.cryptopulse.utils.Constants.COIN_GECKO_BASE_URL
import com.cryptopulse.utils.Constants.ETHERSCAN_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    @Provides
    @Singleton
    @CoinGeckoClient
    fun provideCoinGeckoRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(COIN_GECKO_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinGeckoApi(@CoinGeckoClient retrofit: Retrofit): CoinGeckoApi {
        return retrofit.create(CoinGeckoApi::class.java)
    }

    @Provides
    @Singleton
    @EtherscanClient
    fun provideEtherscanRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ETHERSCAN_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideEtherscanApi(@EtherscanClient retrofit: Retrofit): EtherscanApi {
        return retrofit.create(EtherscanApi::class.java)
    }
}
