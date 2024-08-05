package org.tmartins.currencyexchanges.ui.conversion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.tmartins.currencyexchanges.domain.interfaces.ConvertCurrencyUseCase
import javax.inject.Inject

@HiltViewModel
class ConversionScreenViewModel @Inject constructor(private val convertCurrencyUseCase: ConvertCurrencyUseCase) :
    ViewModel() {

    private val _onConversionValue = MutableStateFlow<String?>("")
    val onConversionValue = _onConversionValue.asStateFlow()

    fun onConvertButtonClicked(amount: Double, from: String, to: String) {
        viewModelScope.launch {
            _onConversionValue.emit(convertCurrencyUseCase.convertCurrency(amount, from, to)?.toString())
        }
    }
}