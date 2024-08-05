package org.tmartins.currencyexchanges.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
data class CurrencyEntity(
    @PrimaryKey val shortName: String,
    val name: String,
)