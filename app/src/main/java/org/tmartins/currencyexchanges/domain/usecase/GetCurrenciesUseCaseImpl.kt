package org.tmartins.currencyexchanges.domain.usecase

import org.tmartins.currencyexchanges.domain.interfaces.ExchangeRepository
import org.tmartins.currencyexchanges.domain.interfaces.GetCurrenciesUseCase
import org.tmartins.currencyexchanges.domain.model.Currency
import javax.inject.Inject

/**
 * Implementation of [GetCurrenciesUseCase]
 */
class GetCurrenciesUseCaseImpl @Inject constructor(private val exchangeRepository: ExchangeRepository) :
    GetCurrenciesUseCase {

    override suspend fun getCurrencies(): List<Currency> {
        return exchangeRepository.getCurrencies().sortedBy { it.shortName }
    }
}