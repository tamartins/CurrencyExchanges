package org.tmartins.currencyexchanges.data.mapper

import org.tmartins.currencyexchanges.data.database.model.CurrencyEntity
import org.tmartins.currencyexchanges.data.network.model.LatestResponse
import org.tmartins.currencyexchanges.domain.model.Currency
import org.tmartins.currencyexchanges.domain.model.Rate

fun LatestResponse.toRates() = rates.map { (currency, rate) -> Rate(currency, rate) }

fun Map<String, String>.toCurrenciesEntity() =
    map { (shortName, name) -> CurrencyEntity(shortName, name) }

fun List<CurrencyEntity>.toCurrencies() = map { Currency(it.shortName, it.name) }