package com.example.dementiaapp.feature.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.RemoveRedEye
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

@Composable
fun ActionButtonVariant(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,

    fontSize: TextUnit = DSTypography.Body.Medium,
    fontWeight: FontWeight = FontWeight.Bold,

    contentColour: Color = DSColours.Surface,
    containerColour: Color = DSColours.Primary,

    contentDescription: String? = null,

    shape: Shape = RoundedCornerShape(DSDimensions.CornerRadius1),

    horizontalPadding: Dp = DSDimensions.Space2,
    verticalPadding: Dp = 0.dp,

    iconSize: Dp = DSDimensions.Icon1,

    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColour,
            contentColor = contentColour
        ),
        contentPadding = PaddingValues(
            horizontal = DSDimensions.Space2,
            vertical = verticalPadding
        ),
        shape = shape,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(DSDimensions.Icon1)
            )
            Spacer(modifier = Modifier.width(DSDimensions.Space1))
            Text(
                text = text,
                fontSize = fontSize,
                fontWeight = fontWeight
            )
        }
    }
}