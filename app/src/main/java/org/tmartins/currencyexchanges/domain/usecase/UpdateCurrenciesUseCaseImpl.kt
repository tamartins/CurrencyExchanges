package org.tmartins.currencyexchanges.domain.usecase

import org.tmartins.currencyexchanges.domain.interfaces.ExchangeRepository
import org.tmartins.currencyexchanges.domain.interfaces.UpdateCurrenciesUseCase
import javax.inject.Inject

class UpdateCurrenciesUseCaseImpl @Inject constructor(private val exchangeRepository: ExchangeRepository) :
    UpdateCurrenciesUseCase {

    override suspend fun updateCurrencies() {
        exchangeRepository.updateCurrencies()
    }
}