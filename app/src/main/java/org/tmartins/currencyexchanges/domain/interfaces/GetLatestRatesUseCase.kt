package org.tmartins.currencyexchanges.domain.interfaces

import org.tmartins.currencyexchanges.domain.model.Rate

/**
 * Use case for retrieving the latest exchange rates order by name.
 */
interface GetLatestRatesUseCase {

    suspend fun getLatestRates(): List<Rate>
}