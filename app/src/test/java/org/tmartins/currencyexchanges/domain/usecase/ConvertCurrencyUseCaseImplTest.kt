package org.tmartins.currencyexchanges.domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.tmartins.currencyexchanges.domain.interfaces.ExchangeRepository
import org.tmartins.currencyexchanges.domain.model.Rate

class ConvertCurrencyUseCaseImplTest {

    private val exchangeRepository = mockk<ExchangeRepository>()
    private val useCase = ConvertCurrencyUseCaseImpl(exchangeRepository)

    @Test
    fun `convertCurrency returns converted rate when successful`() = runBlocking {
        val rate = Rate("EUR", 1.0)
        coEvery { exchangeRepository.rateConversion(any(), any(), any()) } returns rate

        val result = useCase.convertCurrency(10.0, "USD", "EUR")

        assertEquals(1.0, result)
    }

    @Test
    fun `convertCurrency returns null when conversion fails`() = runBlocking {
        coEvery { exchangeRepository.rateConversion(any(), any(), any()) } returns null

        val result = useCase.convertCurrency(10.0, "USD", "EUR")

        assertEquals(null, result)
    }
}