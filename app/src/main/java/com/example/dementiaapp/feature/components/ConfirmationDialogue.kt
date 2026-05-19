package com.example.dementiaapp.feature.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.feature.components.buttons.ActionButton
import com.example.dementiaapp.feature.components.buttons.ActionButtonStyles
import com.example.dementiaapp.localization.LocalizedStrings

@Composable
fun ConfirmationDialogue(
    text: String,
    onConfirm: () -> Unit,
    onDeny: () -> Unit,
    modifier: Modifier = Modifier
) {
    val strings = LocalizedStrings.current

    Surface(
        color = DSColours.OnSurface.copy(alpha = 0.8f),
        modifier = modifier
            .fillMaxSize()
            .clickable(onClick = onDeny)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(DSDimensions.Space4)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(DSDimensions.CornerRadius1))
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { }
                    .border(
                        width = DSDimensions.BorderRadius4,
                        color = DSColours.Warning,
                        shape = RoundedCornerShape(DSDimensions.CornerRadius1)
                    )
                    .background(DSColours.Surface)
                    .padding(DSDimensions.Space4)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Warning,
                        contentDescription = strings.warning,
                        tint = DSColours.Warning,
                        modifier = Modifier
                            .size(DSDimensions.Icon8)
                    )

                    Spacer(modifier = Modifier.width(DSDimensions.Space4))

                    Text(
                        text = strings.warning,
                        fontSize = DSTypography.Headline.Large,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(DSDimensions.Space3))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("${strings.confirmationDialogue} ")
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(text)
                            }
                            append("?")
                        },
                        fontSize = DSTypography.Body.Large
                    )
                }

                Spacer(modifier = Modifier.height(DSDimensions.Space3))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    ActionButton(
                        style = ActionButtonStyles.Deny,
                        onClick = onDeny,

                        horizontalPadding = DSDimensions.Space4,
                        verticalPadding = DSDimensions.Space2,

                        iconSize = DSDimensions.Icon4,
                        fontSize = DSTypography.Headline.Large
                    )

                    ActionButton(
                        style = ActionButtonStyles.Confirm,
                        onClick = onConfirm,

                        horizontalPadding = DSDimensions.Space4,
                        verticalPadding = DSDimensions.Space2,

                        iconSize = DSDimensions.Icon4,
                        fontSize = DSTypography.Headline.Large
                    )
                }
            }
        }
    }
}