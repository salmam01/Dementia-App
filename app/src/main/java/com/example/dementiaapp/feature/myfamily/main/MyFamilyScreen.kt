package com.example.dementiaapp.feature.myfamily.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.RemoveRedEye
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.R
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.Person
import com.example.dementiaapp.feature.components.buttons.StickyActionButton
import com.example.dementiaapp.feature.components.buttons.ActionButtonVariant
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyFamilyScreen(
    onNavigate: (MyFamilyViewModel) -> Unit,
    onAddEntry: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: MyFamilyViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val canAdd = viewModel.hasAccessToAction(FeatureAction.ADD)

    LaunchedEffect(Unit) {
        viewModel.getEntries()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = DSDimensions.AddButtonSpace)
        ) {
            TopText()

            MyFamilyEntriesList(
                entries = state.entries,
                onItemClick = {
                    viewModel.setSelectedEntry(it)
                    onNavigate(viewModel)
                }
            )
        }
        if (canAdd) {
            StickyActionButton(
                text = "Add Entry",
                colour = DSColours.FeatureColours.MyFamily.Primary,
                onAddClick = onAddEntry,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun TopText(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(DSDimensions.Space4)
    ) {
        Text(
            text = "People who are important to you",
            fontSize = DSTypography.Body.Medium,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun MyFamilyEntriesList(
    entries: List<Person>?,
    onItemClick: (Person) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(DSDimensions.Space4),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = DSDimensions.Space2)
    ) {
        entries?.forEach { item ->
            MyFamilyEntryItem(
                item,
                onItemClick = { onItemClick(item) }
            )
        }
    }
}

@Composable
fun MyFamilyEntryItem(
    item: Person,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(DSDimensions.CornerRadius1))
            .border(
                width = DSDimensions.BorderRadius1,
                color = DSColours.FeatureColours.MyFamily.Outline,
                shape = RoundedCornerShape(DSDimensions.CornerRadius1)
            )
            .background(DSColours.FeatureColours.MyFamily.Container)
            .padding(DSDimensions.Space2)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = (item.image ?: painterResource(R.drawable.placeholder_avatar)) as Painter,
                contentDescription = "${item.fullName} Avatar",
                modifier = Modifier
                    .size(
                        height = DSDimensions.Avatar1,
                        width = DSDimensions.Avatar1
                    )
                    .clip(shape = CircleShape)
                    .border(
                        width = DSDimensions.BorderRadius1,
                        color = DSColours.FeatureColours.MyFamily.Outline,
                        shape = CircleShape
                    )
            )

            Spacer(modifier = Modifier.width(DSDimensions.Space4))

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = item.fullName.substringBefore(" "),
                    fontSize = DSTypography.Display.ExtraSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(DSDimensions.Space2))

                Text(
                    text = item.relationShip,
                    fontSize = DSTypography.Body.Large,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        Spacer(modifier = Modifier.height(DSDimensions.Space2))

        Row(

        ) {
            ActionButtonVariant(
                text = "View Details",
                icon = Icons.Rounded.RemoveRedEye,
                containerColour = DSColours.FeatureColours.MyFamily.Primary,
                onClick = onItemClick,
            )
        }
    }
}