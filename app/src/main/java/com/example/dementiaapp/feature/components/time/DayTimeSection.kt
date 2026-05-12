package com.example.dementiaapp.feature.components.time

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.util.time.DayTime
import com.example.dementiaapp.util.time.getDayTimeUI

@Composable
fun DayTimeSection(
    dayTime: DayTime,
    modifier: Modifier = Modifier
) {
    val dayTimeUI = getDayTimeUI(dayTime)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = DSDimensions.Space3)
    ) {
        Icon(
            imageVector = dayTimeUI.icon,
            contentDescription = dayTime.name,
            tint = dayTimeUI.colour,
            modifier = Modifier.size(
                width = DSDimensions.Icon6,
                height = DSDimensions.Icon6,
            )
        )

        Spacer(modifier = Modifier.width(DSDimensions.Space3))

        Text(
            text = dayTimeUI.name,
            fontSize = DSTypography.Headline.Large,
            fontWeight = FontWeight.Bold
        )
    }
}