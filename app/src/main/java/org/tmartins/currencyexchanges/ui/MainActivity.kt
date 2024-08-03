package org.tmartins.currencyexchanges.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.AndroidEntryPoint
import org.tmartins.currencyexchanges.ui.composables.AppNavigation

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    MaterialTheme {
        AppNavigation()
    }
}