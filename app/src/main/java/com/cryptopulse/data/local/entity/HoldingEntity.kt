package com.cryptopulse.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cryptopulse.domain.model.Holding


@Entity(tableName = "holdings")
data class HoldingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val coinId: String,
    val symbol: String,
    val quantity: Double,
    val averageBuyPrice: Double
)