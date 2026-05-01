package com.example.dementiaapp.features.medication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Vaccines
import androidx.compose.material.icons.filled.WaterDrop
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
import com.example.dementiaapp.domain.models.Medication
import com.example.dementiaapp.domain.models.MedicationType
import com.example.dementiaapp.domain.models.toUIString
import com.example.dementiaapp.features.components.CheckBox
import com.example.dementiaapp.features.components.buttons.DeleteButton
import com.example.dementiaapp.features.components.buttons.EditButton


@Composable
fun MedicationItem(
    item: Medication,
    hasCheckBox: Boolean,
    canEdit: Boolean,
    canDelete: Boolean,
    onItemCompleted: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(DSDimensions.CornerRadius1))
            .border(
                width = DSDimensions.BorderRadius2,
                color = DSColours.FeatureColours.Medication.Primary,
                shape = RoundedCornerShape(DSDimensions.CornerRadius1)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(DSDimensions.CornerRadius1))
                .background(DSColours.FeatureColours.Medication.Accent)
                .padding(DSDimensions.Space2)
        ) {
            Text(
                text = item.name,
                fontSize = DSTypography.Headline.Large,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            if (hasCheckBox) {
                CheckBox(
                    itemCompleted = item.completed,
                    onItemCompleted = onItemCompleted
                )
            }
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(DSDimensions.Space2)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .size(height = DSDimensions.ImageBox, width = DSDimensions.ImageBox)
                    .clip(RoundedCornerShape(DSDimensions.CornerRadius1))
                    .background(DSColours.SurfaceVariant)
                    .border(
                        width = DSDimensions.BorderRadius1,
                        color = DSColours.FeatureColours.Medication.Primary,
                        shape = RoundedCornerShape(DSDimensions.CornerRadius1)
                    )
            ) {
                MedicationIcon(medicationType = item.type)
            }

            Spacer(modifier = Modifier.width(DSDimensions.Space2))

            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                MedicationItemDetailRow(
                    label = "Dose",
                    detail = item.dose
                )
                MedicationItemDetailRow(
                    label = "Take at",
                    detail = item.takeAt.toString()
                )
                MedicationItemDetailRow(
                    label = "Repeat",
                    detail = item.repeat.toUIString()
                )
            }
        }

        if (item.notes != null && item.notes.isNotBlank()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = DSDimensions.Space2,
                        end = DSDimensions.Space2,
                        bottom = DSDimensions.Space2
                    )
            ) {
                Text(
                    text = "Notes",
                    fontWeight = FontWeight.Bold,
                    fontSize = DSTypography.Body.Large
                )

                Spacer(modifier = Modifier.height(DSDimensions.Space1))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DSColours.SurfaceVariant)
                        .border(
                            width = DSDimensions.BorderRadius1,
                            color = DSColours.Divider,
                            shape = RoundedCornerShape(DSDimensions.CornerRadius1)
                        )
                        .padding(DSDimensions.Space1)
                ) {
                    Text(
                        text = item.notes,
                        fontWeight = FontWeight.Normal,
                        fontSize = DSTypography.Body.Large
                    )
                }
            }
        }

        if (canEdit) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = DSDimensions.Space1,
                        horizontal = DSDimensions.Space2
                    )
            ) {
                EditButton(
                    onEditClick = onEditClick
                )
                if (canDelete) {
                    Spacer(modifier = Modifier.width(DSDimensions.Space4))
                    DeleteButton(
                        onDeleteClick = onDeleteClick
                    )
                }
            }
        }
    }
}

@Composable
fun MedicationIcon(
    medicationType: MedicationType,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = when (medicationType) {
            MedicationType.Capsule -> Icons.Filled.Medication
            MedicationType.Cream -> Icons.Filled.Spa
            MedicationType.Drops -> Icons.Filled.WaterDrop
            MedicationType.Injection -> Icons.Filled.Vaccines
            MedicationType.Liquid -> Icons.Filled.LocalDrink
            MedicationType.Pill -> Icons.Filled.Medication
        },
        contentDescription = null,
        tint = DSColours.FeatureColours.Medication.Primary,
        modifier = modifier.size(
            height = DSDimensions.Icon10,
            width = DSDimensions.Icon10
        )
    )
}


@Composable
fun MedicationItemDetailRow(
    label: String,
    detail: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxHeight()
    ) {
        Text(
            text = "$label:",
            fontSize = DSTypography.Body.Large,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(DSDimensions.Space1))

        Text(
            text = detail,
            fontSize = DSTypography.Body.Large,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.weight(1f)
        )
    }
}