package org.tmartins.currencyexchanges.ui.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import org.tmartins.currencyexchanges.R

@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
    )
}

@Composable
fun SubTitleText(text: String?) {
    Text(
        text = text.orEmpty(),
        style = MaterialTheme.typography.subtitle1,
        color = Color.Gray,
    )
}

@Composable
fun ErrorText() {
    Text(stringResource(id = R.string.error), fontWeight = FontWeight.Bold)
}