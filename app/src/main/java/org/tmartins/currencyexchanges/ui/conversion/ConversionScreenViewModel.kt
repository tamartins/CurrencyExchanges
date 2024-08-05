package org.tmartins.currencyexchanges.ui.conversion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.tmartins.currencyexchanges.domain.interfaces.ConvertCurrencyUseCase
import org.tmartins.currencyexchanges.domain.model.Currency
import org.tmartins.currencyexchanges.domain.usecase.GetCurrenciesUseCaseImpl
import javax.inject.Inject

@HiltViewModel
class ConversionScreenViewModel @Inject constructor(
    private val getCurrenciesUseCaseImpl: GetCurrenciesUseCaseImpl,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) :
    ViewModel() {

    val currencies: StateFlow<List<Currency>> = flow {
        emit(getCurrenciesUseCaseImpl.getCurrencies())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _onConversionValue = MutableStateFlow<String?>("")
    val onConversionValue = _onConversionValue.asStateFlow()

    fun onConvertButtonClicked(amount: Double, from: String, to: String) {
        viewModelScope.launch {
            _onConversionValue.emit(
                convertCurrencyUseCase.convertCurrency(amount, from, to)?.toString()
            )
        }
    }
}