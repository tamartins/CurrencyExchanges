package org.tmartins.currencyexchanges.domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.tmartins.currencyexchanges.domain.interfaces.ExchangeRepository
import org.tmartins.currencyexchanges.domain.model.Rate

class GetLatestRatesUseCaseImplTest {

    private val exchangeRepository: ExchangeRepository = mockk()
    private val getLatestRatesUseCase = GetLatestRatesUseCaseImpl(exchangeRepository)

    @Test
    fun `getLatestRates returns sorted rates`() = runTest {
        val unsortedRates = listOf(
            Rate("USD", 1.1),
            Rate("EUR", 1.0),
            Rate("GBP", 0.85)
        )
        val sortedRates = listOf(
            Rate("EUR", 1.0),
            Rate("GBP", 0.85),
            Rate("USD", 1.1)
        )

        coEvery { exchangeRepository.getRates() } returns unsortedRates

        val result = getLatestRatesUseCase.getLatestRates()

        assertEquals(sortedRates, result)
    }

    @Test
    fun `getLatestRates returns empty list when repository returns empty list`() = runTest {
        coEvery { exchangeRepository.getRates() } returns emptyList()

        val result = getLatestRatesUseCase.getLatestRates()

        assertEquals(emptyList<Rate>(), result)
    }
}