package org.tmartins.currencyexchanges.domain.interfaces

/**
 * Use case responsible for updating the list of currencies.
 */
interface UpdateCurrenciesUseCase {

    suspend fun updateCurrencies()
}