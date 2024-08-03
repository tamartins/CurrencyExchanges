package org.tmartins.currencyexchanges.domain.interfaces

import org.tmartins.currencyexchanges.domain.model.Rate

/**
 * Repository responsible for managing exchange rate data.
 */
interface ExchangeRepository {

    /**
     * Retrieves a list of exchange rates.
     *
     * @return A list of [Rate] objects.
     */
    suspend fun getRates(): List<Rate>
}