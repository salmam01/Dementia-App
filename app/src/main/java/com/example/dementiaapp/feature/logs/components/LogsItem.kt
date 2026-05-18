package com.example.dementiaapp.feature.logs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.domain.models.feature.LogEntry
import com.example.dementiaapp.util.time.TimeFormatterUtil

@Composable
fun LogsItem(
    log: LogEntry,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(DSDimensions.CornerRadius1))
            .border(
                width = DSDimensions.BorderRadius1,
                color = DSColours.Accent,
                shape = RoundedCornerShape(DSDimensions.CornerRadius1)
            )
            .background(DSColours.SurfaceVariant)
            .padding(
                vertical = DSDimensions.Space2,
                horizontal = DSDimensions.Space4
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = TimeFormatterUtil.formatLocalTime(log.time),
                fontWeight = FontWeight.Bold,
                fontSize = DSTypography.Body.Medium
            )
        }

        HorizontalDivider(
            thickness = DSDimensions.DividerThickness1,
            color = DSColours.OnSurface,
            modifier = Modifier.padding(vertical = DSDimensions.Space2)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(

            ) {
                Text(
                    text = log.logEntryType.toString(),
                    fontWeight = FontWeight.Medium,
                    fontSize = DSTypography.Body.Medium
                )

                Spacer(modifier = Modifier.height(DSDimensions.Space1))

                Text(
                    text = log.content,
                    fontWeight = FontWeight.Normal,
                    fontSize = DSTypography.Body.Medium
                )
            }
        }
    }
}