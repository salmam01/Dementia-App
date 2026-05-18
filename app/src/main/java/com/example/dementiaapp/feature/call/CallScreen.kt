package com.example.dementiaapp.feature.call

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.dementiaapp.localization.LocalizedStrings

@Composable
fun CallScreen(
    modifier: Modifier = Modifier
) {
    val strings = LocalizedStrings.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(strings.placeholderText)
    }
}