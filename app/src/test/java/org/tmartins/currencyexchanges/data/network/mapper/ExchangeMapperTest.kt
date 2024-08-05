package org.tmartins.currencyexchanges.data.network.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import org.tmartins.currencyexchanges.data.mapper.toRates
import org.tmartins.currencyexchanges.data.network.model.LatestResponse
import org.tmartins.currencyexchanges.domain.model.Rate

class ExchangeMapperTest {

    @Test
    fun `toRates returns a list of Rate objects`() {
        val latestResponse = LatestResponse(
            amount = 1.0,
            base = "EUR",
            date = "2024-08-08",
            rates = mapOf(
                "USD" to 1.1,
                "GBP" to 0.85
            )
        )
        val expectedRates = listOf(
            Rate("USD", 1.1),
            Rate("GBP", 0.85)
        )

        val actualRates = latestResponse.toRates()

        assertEquals(expectedRates, actualRates)
    }

    @Test
    fun `toRates returns an empty list when rates are empty`() {
        val latestResponse = LatestResponse(
            amount = 1.0,
            base = "EUR",
            date = "2024-08-08",
            rates = emptyMap()
        )
        val expectedRates = emptyList<Rate>()

        val actualRates = latestResponse.toRates()

        assertEquals(expectedRates, actualRates)
    }
}