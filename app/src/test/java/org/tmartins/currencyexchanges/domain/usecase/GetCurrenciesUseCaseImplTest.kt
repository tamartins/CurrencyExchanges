package org.tmartins.currencyexchanges.domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.tmartins.currencyexchanges.domain.interfaces.ExchangeRepository
import org.tmartins.currencyexchanges.domain.model.Currency

class GetCurrenciesUseCaseImplTest {

    private val exchangeRepository = mockk<ExchangeRepository>()
    private val useCase = GetCurrenciesUseCaseImpl(exchangeRepository)

    @Test
    fun `getCurrencies returns sorted currencies`() = runBlocking {
        val unsortedCurrencies = listOf(
            Currency("EUR", "Euro"),
            Currency("USD", "Dollar"),
            Currency("GBP", "Pound")
        )
        val sortedCurrencies = listOf(
            Currency("EUR", "Euro"),
            Currency("GBP", "Pound"),
            Currency("USD", "Dollar")
        )
        coEvery { exchangeRepository.getCurrencies() } returns unsortedCurrencies

        val result = useCase.getCurrencies()

        assertEquals(sortedCurrencies, result)
    }

    @Test
    fun `getCurrencies returns empty list when repository returns empty list`() = runBlocking {
        coEvery { exchangeRepository.getCurrencies() } returns emptyList()

        val result = useCase.getCurrencies()

        assertEquals(emptyList<Currency>(), result)
    }
}