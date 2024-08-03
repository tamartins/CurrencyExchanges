package org.tmartins.currencyexchanges.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.tmartins.currencyexchanges.R
import org.tmartins.currencyexchanges.domain.model.Rate
import org.tmartins.currencyexchanges.ui.composables.ErrorText
import org.tmartins.currencyexchanges.ui.composables.Loader
import org.tmartins.currencyexchanges.ui.composables.SubTitleText
import org.tmartins.currencyexchanges.ui.composables.TitleText

@Composable
fun RatesList(viewModel: RatesListViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val rates = uiState) {
        is Result.Loading -> {
            Loader()
        }

        is Result.Success<List<Rate>> -> {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_generic))
            ) {
                items(rates.data.size) { index ->
                    RateItem(rates.data[index])
                }
            }
        }

        Result.Error -> {
            ErrorText()
        }
    }
}


@Composable
private fun RateItem(rate: Rate) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_generic)),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TitleText(rate.currency)
            SubTitleText(rate.rate.toString())
        }
    }
}

@Preview
@Composable
fun ItemPreview() {
    RateItem(Rate("EUR", 1.0))
}
