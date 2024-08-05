package org.tmartins.currencyexchanges.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.tmartins.currencyexchanges.data.datasource.ExchangeLocalDataSource
import org.tmartins.currencyexchanges.data.datasource.ExchangeRemoteDataSource
import org.tmartins.currencyexchanges.data.mapper.toCurrencies
import org.tmartins.currencyexchanges.data.mapper.toCurrenciesEntity
import org.tmartins.currencyexchanges.data.mapper.toRates
import org.tmartins.currencyexchanges.domain.interfaces.ExchangeRepository
import org.tmartins.currencyexchanges.domain.model.Currency
import org.tmartins.currencyexchanges.domain.model.Rate

/**
 * Implementation of [ExchangeRepository]
 */
class ExchangeRepositoryImpl(
    private val exchangeRemoteDataSource: ExchangeRemoteDataSource,
    private val exchangeLocalDataSource: ExchangeLocalDataSource,
) :
    ExchangeRepository {

    override suspend fun getRates(): List<Rate> {
        val response = exchangeRemoteDataSource.fetchLatest()
        if (response == null || !response.isSuccessful) return emptyList()
        return response.body()?.toRates() ?: emptyList()
    }

    override suspend fun rateConversion(amount: Double, from: String, to: String): Rate? {
        val response = exchangeRemoteDataSource.fetchLatest(amount, from, to)
        if (response == null || !response.isSuccessful) return null
        return response.body()?.toRates()?.firstOrNull()
    }

    override suspend fun getCurrencies(): List<Currency> {
        val currencies = exchangeLocalDataSource.getAllCurrencies()
        return currencies.toCurrencies()
    }

    override suspend fun updateCurrencies() = withContext(Dispatchers.IO) {
        val response = exchangeRemoteDataSource.fetchCurrencies()
        if (response != null && response.isSuccessful)
            response.body()?.let { currenciesResponse ->
                val currencies = currenciesResponse.toCurrenciesEntity()
                exchangeLocalDataSource.saveCurrencies(currencies)
            }
    }
}