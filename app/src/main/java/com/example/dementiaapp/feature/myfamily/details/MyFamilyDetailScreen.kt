package com.example.dementiaapp.feature.myfamily.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.Person
import com.example.dementiaapp.feature.components.ConfirmationDialogue
import com.example.dementiaapp.feature.components.DetailsRow
import com.example.dementiaapp.feature.components.ProfileCard
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
                ConfirmationDialogue(
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
        ProfileCard(
            name = person.fullName,
            description = person.relationShip,
            image = person.image,

            canEdit = canEdit,
            canDelete = canDelete,

            onEditClick = onEditClick,
            onDeleteClick = onDeleteClick,

            containerColour = DSColours.FeatureColours.MyFamily.Container,
            imageOutlineColour = DSColours.FeatureColours.MyFamily.Outline,
            dividerColour = DSColours.FeatureColours.MyFamily.Outline
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