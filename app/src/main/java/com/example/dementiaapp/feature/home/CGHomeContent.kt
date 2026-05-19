package com.example.dementiaapp.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.dementiaapp.design.DSTypographyNew
import com.example.dementiaapp.domain.models.FeatureType
import com.example.dementiaapp.feature.FeatureItem

@Composable
fun CGHomeContent(
    state: HomeState,
    carePartnerName: String,
    onFeatureClick: (FeatureType) -> Unit,
    modifier: Modifier = Modifier
) {
    CGFeatures(
        carePartnerName = carePartnerName,
        features = state.features,
        onFeatureClick = onFeatureClick
    )
}

@Composable
fun CGFeatures(
    carePartnerName: String,
    features: List<FeatureItem>,
    onFeatureClick: (FeatureType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(DSDimensions.screenPadding)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(DSDimensions.Space4)
        ) {
            features.forEach { featureUI ->
                CGFeatureItem(
                    item = featureUI,
                    carePartnerName = carePartnerName,
                    onClick = { onFeatureClick(featureUI.feature.type) }
                )
            }
        }
    }
}

@Composable
fun CGFeatureItem(
    item: FeatureItem,
    carePartnerName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColour =
        if (item.feature.type == FeatureType.CALL) DSColours.FeatureColours.Call.Primary
        else DSColours.Primary

    val backgroundColour =
        if (item.feature.type == FeatureType.CALL) DSColours.FeatureColours.Call.Container
        else DSColours.Surface

    val itemName =
        if (item.feature.type == FeatureType.CALL) "${item.ui.name} " + carePartnerName
        else item.ui.name

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(DSDimensions.CornerRadius2))
            .background(backgroundColour)
            .border(
                width = DSDimensions.BorderRadius4,
                color = borderColour,
                shape = RoundedCornerShape(DSDimensions.CornerRadius2)
            )
            .clickable(onClick = onClick)
            .padding(DSDimensions.Space4)
    ) {
        Icon(
            imageVector = item.ui.icon,
            contentDescription = item.ui.name,
            tint = item.ui.colour,
            modifier = Modifier.size(DSDimensions.Icon7)
        )
        Spacer(modifier = Modifier.width(DSDimensions.Space3))
        Text(
            text = itemName,
            color = DSColours.OnSurface,
            fontSize = DSTypography.Body.Large,
            fontWeight = FontWeight.Bold
        )
    }
}