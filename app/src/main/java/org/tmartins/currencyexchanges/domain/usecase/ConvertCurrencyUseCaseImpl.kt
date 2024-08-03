package org.tmartins.currencyexchanges.domain.usecase

import org.tmartins.currencyexchanges.domain.interfaces.ConvertCurrencyUseCase
import org.tmartins.currencyexchanges.domain.interfaces.ExchangeRepository
import javax.inject.Inject

/**
 * Implementation of [ConvertCurrencyUseCase]
 */
class ConvertCurrencyUseCaseImpl @Inject constructor(private val exchangeRepository: ExchangeRepository) :
    ConvertCurrencyUseCase {

    override suspend fun convertCurrency(amount: Double, from: String, to: String): Double? {
        return exchangeRepository.rateConversion(amount, from, to)?.rate
    }
}