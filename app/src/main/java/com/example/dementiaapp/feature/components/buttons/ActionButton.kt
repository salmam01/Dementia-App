package com.example.dementiaapp.feature.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.ChatBubble
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.localization.LocalizedStrings

@Composable
fun ActionButton(
    style: ActionButtonStyle,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,

    fontSize: TextUnit = DSTypography.Body.Small,
    fontWeight: FontWeight = FontWeight.Bold,

    contentDescription: String? = null,

    shape: Shape = RoundedCornerShape(DSDimensions.CornerRadius1),

    horizontalPadding: Dp = DSDimensions.Space2,
    verticalPadding: Dp = 0.dp,
    contentSpacing: Dp = DSDimensions.Space1,

    iconSize: Dp = DSDimensions.Icon1
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = style.containerColour,
            contentColor = style.contentColour
        ),
        contentPadding = PaddingValues(
            horizontal = horizontalPadding,
            vertical = verticalPadding
        ),
        shape = shape,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            Icon(
                imageVector = style.icon,
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(iconSize)
            )
            Spacer(modifier = Modifier.width(contentSpacing))
            Text(
                text = style.text,
                fontSize = fontSize,
                fontWeight = fontWeight
            )
        }
    }
}

data class ActionButtonStyle(
    val text: String,
    val icon: ImageVector,
    val contentDescription: String,
    val containerColour: Color,
    val contentColour: Color = DSColours.Surface
)

val strings = LocalizedStrings.current

object ActionButtonStyles {
    val Edit = ActionButtonStyle(
        text = strings.edit,
        icon = Icons.Rounded.Edit,
        contentDescription = strings.edit,
        containerColour = DSColours.EditPrimary
    )

    val Delete = ActionButtonStyle(
        text = strings.delete,
        icon = Icons.Rounded.Delete,
        contentDescription = strings.delete,
        containerColour = DSColours.NegativeActionPrimary
    )

    val Confirm = ActionButtonStyle(
        text = strings.yes,
        icon = Icons.Rounded.Check,
        contentDescription = strings.yes,
        containerColour = DSColours.PositiveActionPrimary
    )

    val Deny = ActionButtonStyle(
        text = strings.no,
        icon = Icons.Rounded.Close,
        contentDescription = strings.no,
        containerColour = DSColours.NegativeActionPrimary
    )

    val Filter = ActionButtonStyle(
        text = strings.filter,
        icon = Icons.Rounded.Tune,
        contentDescription = strings.filter,
        containerColour = DSColours.FeatureColours.Calendar.Primary
    )

    val ApplyFilter = ActionButtonStyle(
        text = strings.applyFilters,
        icon = Icons.Rounded.Check,
        contentDescription = strings.applyFilters,
        containerColour = DSColours.FeatureColours.Calendar.Primary
    )

    val Message = ActionButtonStyle(
        text = strings.message,
        icon = Icons.Rounded.ChatBubble,
        contentDescription = strings.message,
        containerColour = DSColours.FeatureColours.CarePartner.Primary
    )

    val Call = ActionButtonStyle(
        text = strings.call,
        icon = Icons.Rounded.Call,
        contentDescription = strings.call,
        containerColour = DSColours.FeatureColours.Call.Primary
    )
}