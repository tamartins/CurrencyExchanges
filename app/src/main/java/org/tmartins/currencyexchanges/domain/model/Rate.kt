package org.tmartins.currencyexchanges.domain.model

/**
 * Represents a currency exchange rate.
 *
 * @param currency The currency code.
 * @param rate The exchange rate.
 */
data class Rate(val currency: String, val rate: Double)
