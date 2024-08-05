package org.tmartins.currencyexchanges.ui.list

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.tmartins.currencyexchanges.domain.interfaces.GetLatestRatesUseCase
import org.tmartins.currencyexchanges.domain.model.Rate

@ExperimentalCoroutinesApi
class RatesListViewModelTest {

    private val getLatestRatesUseCase = mockk<GetLatestRatesUseCase>()
    private lateinit var viewModel: RatesListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = RatesListViewModel(getLatestRatesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `uiState emits Loading initially`() = runTest(testDispatcher) {
        val initialState = viewModel.uiState.first()
        assertEquals(Result.Loading, initialState)
    }

    @Test
    fun `uiState emits Success when getRates is successful`() = runTest(testDispatcher) {
        val rates = listOf(Rate("EUR", 1.5))
        coEvery { getLatestRatesUseCase.getLatestRates() } returns rates

        val state = viewModel.uiState.first { it is Result.Success }
        assertEquals(Result.Success(rates), state)
    }

    @Test
    fun `uiState emits Error when getRates throws exception`() = runTest(testDispatcher) {
        coEvery { getLatestRatesUseCase.getLatestRates() } throws Exception("Network error")

        val state = viewModel.uiState.first { it is Result.Error }
        assertEquals(Result.Error, state)
    }
}