package org.tmartins.currencyexchanges.data.repository

import org.tmartins.currencyexchanges.data.datasource.ExchangeRemoteDataSource
import org.tmartins.currencyexchanges.data.network.mapper.toRates
import org.tmartins.currencyexchanges.domain.interfaces.ExchangeRepository
import org.tmartins.currencyexchanges.domain.model.Rate

/**
 * Implementation of [ExchangeRepository]
 */
class ExchangeRepositoryImpl(private val exchangeRemoteDataSource: ExchangeRemoteDataSource) :
    ExchangeRepository {

    override suspend fun getRates(): List<Rate> {
        val response = exchangeRemoteDataSource.fetchLatest()
        if (response == null || !response.isSuccessful) return emptyList()
        return response.body()?.toRates() ?: emptyList()
    }
}