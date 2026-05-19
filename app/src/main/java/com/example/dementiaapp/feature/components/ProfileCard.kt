package com.example.dementiaapp.feature.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.dementiaapp.R
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.feature.components.buttons.ActionButton
import com.example.dementiaapp.feature.components.buttons.ActionButtonStyles

@Composable
fun ProfileCard(
    name: String,
    description: String,
    image: String?,

    modifier: Modifier = Modifier,

    canEdit: Boolean,
    canDelete: Boolean,

    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,

    containerColour: Color = DSColours.Tertiary,
    imageOutlineColour: Color = DSColours.OnPrimary,
    dividerColour: Color = DSColours.Divider
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(containerColour)
            .padding(DSDimensions.Space4)
    ) {
        Image(
            painter = (image ?: painterResource(R.drawable.placeholder_avatar)) as Painter,
            contentDescription = "$name's Image",
            modifier = Modifier
                .size(DSDimensions.Avatar3)
                .clip(CircleShape)
                .border(
                    width = DSDimensions.BorderRadius1,
                    color = imageOutlineColour,
                    shape = CircleShape
                )
        )

        Spacer(modifier = Modifier.width(DSDimensions.Space4))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = name,
                fontSize = DSTypography.Display.Small,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(DSDimensions.Space1))
            Text(
                text = description,
                fontSize = DSTypography.Body.Large,
                fontWeight = FontWeight.Normal
            )

            if (canEdit) {
                Spacer(modifier = Modifier.height(DSDimensions.Space2))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ActionButton(
                        style = ActionButtonStyles.Edit,
                        onClick = onEditClick
                    )

                    if (canDelete) {
                        Spacer(modifier = Modifier.width(DSDimensions.Space4))
                        ActionButton(
                            style = ActionButtonStyles.Delete,
                            onClick = onDeleteClick
                        )
                    }
                }
            }
        }
    }
    HorizontalDivider(
        thickness = DSDimensions.DividerThickness2,
        color = dividerColour
    )
}