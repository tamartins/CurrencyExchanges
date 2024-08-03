package org.tmartins.currencyexchanges.data.repository

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.tmartins.currencyexchanges.data.datasource.ExchangeRemoteDataSource
import org.tmartins.currencyexchanges.data.network.mapper.toRates
import org.tmartins.currencyexchanges.data.network.model.LatestResponse
import org.tmartins.currencyexchanges.domain.model.Rate
import retrofit2.Response

class ExchangeRepositoryImplTest {

    private val exchangeRemoteDataSource: ExchangeRemoteDataSource = mockk()
    private val exchangeRepositoryImpl = ExchangeRepositoryImpl(exchangeRemoteDataSource)

    @Test
    fun `getRates returns empty list when response is null`() = runTest {
        coEvery { exchangeRemoteDataSource.fetchLatest() } returns null

        val result = exchangeRepositoryImpl.getRates()

        assertEquals(emptyList<Rate>(), result)
    }

    @Test
    fun `getRates returns empty list when response is not successful`() = runTest {
        val response = mockk<Response<LatestResponse?>>()
        coEvery { exchangeRemoteDataSource.fetchLatest() } returns response
        coEvery { response.isSuccessful } returns false

        val result = exchangeRepositoryImpl.getRates()

        assertEquals(emptyList<Rate>(), result)
    }

    @Test
    fun `getRates returns empty list when response body is null`() = runTest {
        val response = mockk<Response<LatestResponse?>>()
        coEvery { exchangeRemoteDataSource.fetchLatest() } returns response
        coEvery { response.isSuccessful } returns true
        coEvery { response.body() } returns null

        val result = exchangeRepositoryImpl.getRates()

        assertEquals(emptyList<Rate>(), result)
    }

    @Test
    fun `getRates returns rates when response is successful and body is not null`() = runTest {
        val response = mockk<Response<LatestResponse?>>()
        val currencyResponse = LatestResponse(
            amount = 1.0,
            base = "EUR",
            date = "2024-08-02",
            rates = mapOf("USD" to 1.1)
        )
        val expectedRates = currencyResponse.toRates()

        coEvery { exchangeRemoteDataSource.fetchLatest() } returns response
        coEvery { response.isSuccessful } returns true
        coEvery { response.body() } returns currencyResponse

        val result = exchangeRepositoryImpl.getRates()

        assertEquals(expectedRates, result)
    }
}