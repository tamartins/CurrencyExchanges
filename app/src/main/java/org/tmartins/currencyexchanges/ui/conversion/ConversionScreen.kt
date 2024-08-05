package org.tmartins.currencyexchanges.ui.conversion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.tmartins.currencyexchanges.R

@Composable
fun ConversionScreen(viewModel: ConversionScreenViewModel = hiltViewModel()) {
    var amount by remember { mutableStateOf("") }
    var from by remember { mutableStateOf("") }
    var to by remember { mutableStateOf("") }
    val conversionValue by viewModel.onConversionValue.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_generic)),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = amount,
            onValueChange = { amount = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(stringResource(id = R.string.amount)) })

        ViewsSpace()

        TextField(
            value = from.uppercase(),
            onValueChange = { if (it.length <= 3) from = it },
            label = { Text(stringResource(id = R.string.convert_from)) })

        ViewsSpace()

        TextField(
            value = to.uppercase(),
            onValueChange = { if (it.length <= 3) to = it },
            label = { Text(stringResource(id = R.string.convert_to)) })

        ViewsSpace()

        Text(text = conversionValue ?: stringResource(id =R.string.error))

        ViewsSpace()

        Button(
            onClick = {
                viewModel.onConvertButtonClicked(
                    amount.toDoubleOrNull() ?: 0.0,
                    from,
                    to
                )
            },
            enabled = amount.isNotEmpty() && from.isNotEmpty() && to.isNotEmpty()
        ) {
            Text(stringResource(id = R.string.convert))
        }
    }
}

@Composable
fun ViewsSpace() {
    Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_generic)))
}

@Preview(showBackground = true)
@Composable
fun ConversionScreenPreview() {
    ConversionScreen()
}