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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SentimentSatisfied
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.domain.models.FeatureType
import com.example.dementiaapp.feature.FeatureItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    onNavigate: (FeatureType) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val features = state.features

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DSColours.Accent)
    ) {
        HomeTopBar(state)
        Features(
            features,
            onFeatureClick = onNavigate
        )
    }
}

@Composable
fun HomeTopBar(
    state: HomeState,
    modifier: Modifier = Modifier
) {
    val firstName = state.user.name.substringBefore(" ")
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RectangleShape,
                clip = false
            )
            .background(DSColours.Primary)
            .padding(vertical = DSDimensions.Space4),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(DSDimensions.Space2),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Hello, $firstName!",
                color = DSColours.OnPrimary,
                fontSize = DSTypography.Display.Medium,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = Icons.Outlined.SentimentSatisfied,
                contentDescription = null,
                tint = DSColours.OnPrimary,
                modifier = Modifier.size(DSDimensions.Icon6)
            )
        }

        Spacer(modifier = Modifier.height(DSDimensions.Space4))

        Text(
            text = "Today is ${state.currentDay}, ${state.currentDate}",
            color = DSColours.OnPrimary,
            fontSize = DSTypography.Headline.Medium,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(DSDimensions.Space2))

        Row(
            horizontalArrangement = Arrangement.spacedBy(DSDimensions.Space2),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.WbSunny,
                contentDescription = null,
                tint = DSColours.DayTime,
                modifier = Modifier.size(DSDimensions.Icon6)
            )
            Text(
                text = "${state.currentTime}",
                color = DSColours.OnPrimary,
                fontSize = DSTypography.Display.Medium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun Features(
    features: List<FeatureItem>,
    onFeatureClick: (FeatureType) -> Unit,
    modifier: Modifier = Modifier
) {
    val (even, odd) = features
        .withIndex()
        .partition { it.index % 2 == 0 }
        .let { (even, odd) ->
            even.map { it.value } to odd.map { it.value }
        }

    println(even)
    println(odd)

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(DSDimensions.Space4)
        ) {
            even.forEach { featureUI ->
                FeatureItems(
                    item = featureUI,
                    onClick = { onFeatureClick(featureUI.feature.type) }
                )
            }
        }

        Spacer(modifier = Modifier.width(DSDimensions.Space4))

        Column(
            verticalArrangement = Arrangement.spacedBy(DSDimensions.Space4)
        ) {
            odd.forEach { featureUI ->
                FeatureItems(
                    featureUI,
                    onClick = { onFeatureClick(featureUI.feature.type) }
                )
            }
        }
    }
}

@Composable
fun FeatureItems(
    item: FeatureItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColour = if (item.feature.type == FeatureType.CALL) {
        DSColours.FeatureColours.Call.Primary
    } else {
        DSColours.Primary
    }

    val backgroundColour = if (item.feature.type == FeatureType.CALL) {
        DSColours.FeatureColours.Call.Container
    } else {
        DSColours.Surface
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .size(
                height = DSDimensions.Element1,
                width = DSDimensions.Element1)
            .clip(RoundedCornerShape(DSDimensions.CornerRadius3))
            .background(backgroundColour)
            .border(
                width = DSDimensions.BorderRadius4,
                color = borderColour,
                shape = RoundedCornerShape(DSDimensions.CornerRadius3)
            )
            .clickable(onClick = onClick)
            .padding(DSDimensions.Space2)
    ) {
        Icon(
            imageVector = item.ui.icon,
            contentDescription = null,
            tint = item.ui.colour,
            modifier = Modifier.size(DSDimensions.Icon9)
        )
        Spacer(modifier = Modifier.height(DSDimensions.Space1))
        Text(
            text = item.ui.name,
            color = DSColours.OnSurface,
            fontSize = DSTypography.Body.Large,
            fontWeight = FontWeight.Bold
        )
    }
}