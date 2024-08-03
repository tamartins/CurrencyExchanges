package org.tmartins.currencyexchanges.domain.interfaces

/**
 * Use case for converting currencies.
 */
interface ConvertCurrencyUseCase {

    /**
     * Converts the given amount from one currency to another.
     *
     * @param amount The amount to convert.
     * @param from The currency code to convert from.
     * @param to The currency code to convert to.
     *
     * @return The converted amount, or null if the conversion fails.
     */
    suspend fun convertCurrency(amount: Double, from: String, to: String): Double?
}