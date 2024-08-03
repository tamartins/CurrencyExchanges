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

    /**
     * Converts an amount from one currency to another.
     *
     * @param amount The amount to convert.
     * @param from The currency code to convert from.
     * @param to The currency code to convert to.
     * @return A [Rate] object representing the converted amount, or `null` if the conversion fails.
     */
    suspend fun rateConversion(amount: Double, from: String, to: String): Rate?
}