package org.tmartins.currencyexchanges.data.datasource

import org.tmartins.currencyexchanges.data.database.AppDatabase
import org.tmartins.currencyexchanges.data.database.model.CurrencyEntity

/**
 * Local data source responsible for storing and retrieving exchange rate data locally.
 */
class ExchangeLocalDataSource(private val appDatabase: AppDatabase) {

    /**
     * Saves a list of currencies to the local database.
     */
    fun saveCurrencies(currencies: List<CurrencyEntity>) {
        appDatabase.currencyDao().insertAllCurrencies(currencies)
    }

    /**
     * Retrieves a list of all currencies from the local database.
     */
    suspend fun getAllCurrencies(): List<CurrencyEntity> {
        return appDatabase.currencyDao().getAllCurrencies()
    }
}