package com.cryptopulse.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoinGeckoClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EtherscanClient