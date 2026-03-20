package com.cryptopulse.di

import com.cryptopulse.data.remote.repository.MarketRepositoryImpl
import com.cryptopulse.data.remote.repository.PortfolioRepositoryImpl
import com.cryptopulse.data.remote.repository.WalletRepositoryImpl
import com.cryptopulse.domain.repository.MarketRepository
import com.cryptopulse.domain.repository.PortfolioRepository
import com.cryptopulse.domain.repository.WalletRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMarketRepository(
        impl: MarketRepositoryImpl
    ): MarketRepository

    @Binds
    @Singleton
    abstract fun bindPortfolioRepository(
        impl: PortfolioRepositoryImpl
    ): PortfolioRepository

    @Binds
    @Singleton
    abstract fun bindWalletRepository(
        impl: WalletRepositoryImpl
    ): WalletRepository
}