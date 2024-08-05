package org.tmartins.currencyexchanges.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.tmartins.currencyexchanges.data.database.dao.CurrencyDAO
import org.tmartins.currencyexchanges.data.database.model.CurrencyEntity

@Database(entities = [CurrencyEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDAO
}