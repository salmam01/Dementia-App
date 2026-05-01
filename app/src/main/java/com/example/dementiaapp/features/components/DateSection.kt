package com.example.dementiaapp.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.util.time.TimeFormatterUtil
import com.example.dementiaapp.domain.time.TimeProvider.toTimeAdverb
import java.time.LocalDate

@Composable
fun DateSection(
    date: LocalDate,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val timeAdverb = date.toTimeAdverb()
    val formattedDate =
        if (timeAdverb == null)
            TimeFormatterUtil.formatDateToMonthDay(date)
        else
            "${TimeFormatterUtil.formatTimeAdverb(timeAdverb)}, " +
            TimeFormatterUtil.formatDateToMonthDay(date)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(DSColours.SurfaceVariant)
            .border(
                width = DSDimensions.BorderRadius1,
                color = DSColours.Divider,
                shape = RoundedCornerShape(0)
            )
            .padding(
                DSDimensions.Space2
            )
    ) {
        IconButton(
            onClick = onPreviousClick
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBackIosNew,
                contentDescription = null,
                tint = DSColours.OnSurface,
                modifier = Modifier.size(DSDimensions.Icon4)
            )
        }

        Text(
            text = formattedDate,
            fontSize = DSTypography.Display.ExtraSmall,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = onNextClick
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBackIosNew,
                contentDescription = null,
                tint = DSColours.OnSurface,
                modifier = Modifier
                    .size(DSDimensions.Icon4)
                    .scale(scaleX = -1f, scaleY = 1f)
            )
        }
    }
}