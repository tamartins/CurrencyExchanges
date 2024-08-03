package org.tmartins.currencyexchanges.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import org.tmartins.currencyexchanges.domain.interfaces.GetLatestRatesUseCase
import org.tmartins.currencyexchanges.domain.model.Rate
import javax.inject.Inject

@HiltViewModel
class RatesListViewModel @Inject constructor(private val getLatestRatesUseCase: GetLatestRatesUseCase) :
    ViewModel() {

    val uiState: StateFlow<Result<List<Rate>>> = flow {
        emit(Result.Loading)
        emit(getRates())
    }.catch { emit(Result.Error) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading)

    private suspend fun getRates(): Result.Success<List<Rate>> {
        return Result.Success(getLatestRatesUseCase.getLatestRates())
    }
}

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data object Error : Result<Nothing>
    data object Loading : Result<Nothing>
}