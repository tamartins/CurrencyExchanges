package org.tmartins.currencyexchanges.domain.usecase

import org.tmartins.currencyexchanges.domain.interfaces.ExchangeRepository
import org.tmartins.currencyexchanges.domain.interfaces.GetLatestRatesUseCase
import org.tmartins.currencyexchanges.domain.model.Rate
import javax.inject.Inject

/**
 * Implementation of [GetLatestRatesUseCase]
 */
class GetLatestRatesUseCaseImpl @Inject constructor(private val exchangeRepository: ExchangeRepository) :
    GetLatestRatesUseCase {

    override suspend fun getLatestRates(): List<Rate> {
        return exchangeRepository.getRates().sortedBy { it.currency }
    }
}