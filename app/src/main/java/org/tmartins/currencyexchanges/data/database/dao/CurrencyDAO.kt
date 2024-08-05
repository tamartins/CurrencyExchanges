package org.tmartins.currencyexchanges.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.tmartins.currencyexchanges.data.database.model.CurrencyEntity


@Dao
interface CurrencyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCurrencies(currencies: List<CurrencyEntity>)

    @Query("SELECT * FROM currencies")
    suspend fun getAllCurrencies(): List<CurrencyEntity>
}