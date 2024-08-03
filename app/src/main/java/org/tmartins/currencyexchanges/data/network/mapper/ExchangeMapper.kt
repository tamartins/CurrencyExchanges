package org.tmartins.currencyexchanges.data.network.mapper

import org.tmartins.currencyexchanges.data.network.model.LatestResponse
import org.tmartins.currencyexchanges.domain.model.Rate

fun LatestResponse.toRates() = rates.map { (currency, rate) -> Rate(currency, rate) }