package com.cryptopulse.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cryptopulse.data.local.dao.HoldingDao
import com.cryptopulse.data.local.entity.HoldingEntity

@Database(
    entities = [HoldingEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CryptoPulseDatabase : RoomDatabase() {
    abstract fun holdingDao(): HoldingDao
}