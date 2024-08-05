package org.tmartins.currencyexchanges.domain.model

/**
 * Represents a currency exchange rate.
 *
 * @param shortName The short name of the currency.(eg:"EUR")
 * @param name The full name of the currency.
 */
data class Currency(val shortName: String, val name: String)