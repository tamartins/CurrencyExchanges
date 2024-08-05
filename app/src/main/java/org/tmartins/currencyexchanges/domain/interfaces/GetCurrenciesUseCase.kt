package org.tmartins.currencyexchanges.domain.interfaces

import org.tmartins.currencyexchanges.domain.model.Currency

/**
 * Use case for retrieving a list of currencies.
 */
interface GetCurrenciesUseCase {

    suspend fun getCurrencies(): List<Currency>
}