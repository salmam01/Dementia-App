package com.example.dementiaapp.feature.profile.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography


@Composable
fun ProfileItem(
    title: String,
    icon: ImageVector,
    description: String,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(DSDimensions.CornerRadius1),
        color = DSColours.Surface,
        shadowElevation = 4.dp,
        tonalElevation = 0.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = DSDimensions.Space2)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .border(
                    width = DSDimensions.BorderRadius1,
                    color = DSColours.Divider,
                    shape = RoundedCornerShape(DSDimensions.CornerRadius1)
                )
                .padding(DSDimensions.Space4)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = DSColours.Primary,
                modifier = Modifier
                    .size(DSDimensions.Icon7)
            )

            Spacer(modifier = Modifier.width(DSDimensions.Space4))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    fontSize = DSTypography.Body.Large,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(DSDimensions.Space1))

                Text(
                    text = description,
                    fontSize = DSTypography.Body.Small,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start
                )
            }

            Spacer(modifier = Modifier.width(DSDimensions.Space4))

            IconButton(
                onClick = onItemClick
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    contentDescription = null,
                    tint = DSColours.Primary,
                    modifier = Modifier
                        .size(DSDimensions.Icon5)
                        .scale(scaleX = -1f, scaleY = 1f)
                )
            }
        }
    }
}
