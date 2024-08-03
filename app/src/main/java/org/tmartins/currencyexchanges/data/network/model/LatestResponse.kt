package org.tmartins.currencyexchanges.data.network.model

/**
 * Represents an API response containing latest exchange rates.
 *
 * @param amount The base amount.
 * @param base The base currency.
 * @param date The date of the exchange rates.
 * @param rates A map of currency codes to exchange rates.
 */
data class LatestResponse(
    val amount: Double,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)