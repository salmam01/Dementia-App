package com.example.dementiaapp.feature.myfamily.details

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.R
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.Person
import com.example.dementiaapp.feature.components.ConfirmationDialog
import com.example.dementiaapp.feature.components.buttons.ActionButton
import com.example.dementiaapp.feature.components.buttons.ActionButtonStyles
import com.example.dementiaapp.feature.myfamily.main.MyFamilyViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun MyFamilyDetailScreen(
    onReturn: () -> Unit,
    onEditEntry: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: MyFamilyViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val selectedEntry = state.selectedEntry
    val canAdd = viewModel.hasAccessToAction(FeatureAction.ADD)
    val canEdit = canAdd
            && viewModel.hasAccessToAction(FeatureAction.EDIT)
    val canDelete = canEdit
            && viewModel.hasAccessToAction(FeatureAction.DELETE)

    if (selectedEntry == null) {
        onReturn()
    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                EntryDetails(
                    person = selectedEntry,
                    canEdit = canEdit,
                    canDelete = canDelete,
                    onEditClick = { onEditEntry(state.selectedEntry?.id) },
                    onDeleteClick = { viewModel.showConfirmationDialog() }
                )
            }

            if (state.showConfirmationDialog) {
                val entryName = selectedEntry.fullName.substringAfter(" ")
                ConfirmationDialog(
                    text = "delete \"$entryName\"",
                    onConfirm = { viewModel.deleteEntry(selectedEntry) },
                    onDeny = { viewModel.hideConfirmationDialog() }
                )
            }
        }
    }
}

@Composable
fun EntryDetails(
    person: Person,
    canEdit: Boolean,
    canDelete: Boolean,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val age = person.birthday?.let {
        LocalDate.now().year - it.year
    }.toString()

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        EntryDetailsHeader(
            person = person,
            canEdit = canEdit,
            canDelete = canDelete,
            onEditClick = onEditClick,
            onDeleteClick = onDeleteClick
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            DetailsRow(
                label = "Full Name",
                detail = person.fullName
            )
            HorizontalDivider(
                thickness = DSDimensions.DividerThickness1,
                color = DSColours.Divider
            )
            DetailsRow(
                label = "Relationship",
                detail = person.relationShip
            )
            HorizontalDivider(
                thickness = DSDimensions.DividerThickness1,
                color = DSColours.Divider
            )
            DetailsRow(
                label = "Nickname",
                detail = person.nickName
            )
            HorizontalDivider(
                thickness = DSDimensions.DividerThickness1,
                color = DSColours.Divider
            )
            DetailsRow(
                label = "Age",
                detail = age
            )
            HorizontalDivider(
                thickness = DSDimensions.DividerThickness1,
                color = DSColours.Divider
            )
            DetailsRow(
                label = "Address",
                detail = person.address
            )
            HorizontalDivider(
                thickness = DSDimensions.DividerThickness1,
                color = DSColours.Divider
            )
            DetailsRow(
                label = "Number",
                detail = person.number
            )
            HorizontalDivider(
                thickness = DSDimensions.DividerThickness1,
                color = DSColours.Divider
            )
            DetailsRow(
                label = "Hobbies",
                detail = person.hobbies
            )
            HorizontalDivider(
                thickness = DSDimensions.DividerThickness1,
                color = DSColours.Divider
            )
            DetailsRow(
                label = "Favourite Colour",
                detail = person.favouriteColour
            )
            HorizontalDivider(
                thickness = DSDimensions.DividerThickness1,
                color = DSColours.Divider
            )
            DetailsRow(
                label = "Favourite Book",
                detail = person.favouriteBook
            )
            HorizontalDivider(
                thickness = DSDimensions.DividerThickness1,
                color = DSColours.Divider
            )
            DetailsRow(
                label = "Favourite Food",
                detail = person.favouriteFood
            )
            HorizontalDivider(
                thickness = DSDimensions.DividerThickness1,
                color = DSColours.Divider
            )
            DetailsRow(
                label = "Favourite Music",
                detail = person.favouriteMusic
            )
            HorizontalDivider(
                thickness = DSDimensions.DividerThickness1,
                color = DSColours.Divider
            )
        }
    }
}

@Composable
fun EntryDetailsHeader(
    person: Person,
    canEdit: Boolean,
    canDelete: Boolean,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(DSColours.FeatureColours.MyFamily.Container)
            .padding(DSDimensions.Space4)
    ) {

        Image(
            painter = (person.image ?: painterResource(R.drawable.placeholder_avatar)) as Painter,
            contentDescription = null,
            modifier = Modifier
                .size(DSDimensions.Avatar2)
                .clip(CircleShape)
                .border(
                    width = DSDimensions.BorderRadius1,
                    color = DSColours.FeatureColours.MyFamily.Outline,
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
                text = person.fullName,
                fontSize = DSTypography.Display.Small,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(DSDimensions.Space1))
            Text(
                text = person.relationShip,
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
        color = DSColours.FeatureColours.MyFamily.Outline
    )
}

@Composable
fun DetailsRow(
    label: String,
    detail: String?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(
                horizontal = DSDimensions.Space4,
                vertical = DSDimensions.Space5
            )
    ) {
        Text(
            text = label,
            fontSize = DSTypography.Body.Large,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(DSDimensions.Space2))

        Text(
            text = detail ?: "",
            fontSize = DSTypography.Body.Large,
            fontWeight = FontWeight.Normal
        )
    }
}