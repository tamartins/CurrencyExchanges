package org.tmartins.currencyexchanges.data.datasource

import org.tmartins.currencyexchanges.data.network.ApiService

/**
 * Remote data source responsible for fetching exchanges data from an API.
 */
class ExchangeRemoteDataSource(private val apiService: ApiService) {

    suspend fun fetchLatest(
        amount: Double? = null,
        from: String? = null,
        to: String? = null,
    ) = apiService.getLatest(amount, from, to)
}