package org.tmartins.currencyexchanges.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.tmartins.currencyexchanges.domain.interfaces.UpdateCurrenciesUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val updateCurrenciesUseCase: UpdateCurrenciesUseCase,
) :
    ViewModel() {
    private val _onSplashScreenFinished = MutableStateFlow(false)
    val onSplashScreenFinished = _onSplashScreenFinished.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            updateCurrenciesUseCase.updateCurrencies()
            _onSplashScreenFinished.value = true
        }
    }
}