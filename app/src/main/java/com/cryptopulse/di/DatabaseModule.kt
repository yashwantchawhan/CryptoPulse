package com.cryptopulse.di


import android.content.Context
import androidx.room.Room
import com.cryptopulse.data.local.dao.HoldingDao
import com.cryptopulse.data.local.db.CryptoPulseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): CryptoPulseDatabase {
        return Room.databaseBuilder(
            context,
            CryptoPulseDatabase::class.java,
            "crypto_pulse_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideHoldingDao(db: CryptoPulseDatabase): HoldingDao = db.holdingDao()
}