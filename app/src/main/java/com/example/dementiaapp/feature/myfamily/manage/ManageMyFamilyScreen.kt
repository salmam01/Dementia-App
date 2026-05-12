package com.example.dementiaapp.feature.myfamily.manage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.domain.models.Person
import com.example.dementiaapp.feature.components.buttons.FormButtons
import com.example.dementiaapp.feature.components.TitleSection
import com.example.dementiaapp.feature.components.form.DatePickerForm
import com.example.dementiaapp.feature.components.form.TextFieldForm
import org.koin.compose.viewmodel.koinViewModel
import java.time.LocalDate

@Composable
fun ManageMyFamilyScreen(
    selectedEntryId: String?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: ManageMyFamilyViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (selectedEntryId != null) {
        viewModel.getEntryById(id = selectedEntryId)
    }

    val isEditing = state.isEditing
    val title =
        if (isEditing) "Edit ${state.selectedEntry?.fullName}"
        else "Add Person"

    val icon =
        if (isEditing) Icons.Rounded.Edit
        else Icons.Rounded.Add

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TitleSection(
            title = title,
            icon = icon,
            colour = DSColours.FeatureColours.MyFamily.Primary
        )

        ManageMyFamilyForm(
            draftEntry = state.draftEntry,
            onFullNameChange = { viewModel.setFullName(it) },
            onNickNameChange = { viewModel.setNickName(it) },
            onRelationShipChange = { viewModel.setRelationShip(it) },
            onBirthdaySelected = { viewModel.setBirthday(it) },
            onAddressChange = { viewModel.setAddress(it) },
            onNumberChange = { viewModel.setNumber(it) },
            onHobbiesChange = { viewModel.setHobbies(it) },
            onFavouriteColourChange = { viewModel.setFavouriteColour(it) },
            onFavouriteBookChange = { viewModel.setFavouriteBook(it) },
            onFavouriteFoodChange = { viewModel.setFavouriteFood(it) },
            onFavouriteMusicChange = { viewModel.setFavouriteMusic(it) },
            modifier = Modifier.weight(1f)
        )

        FormButtons(
            colour = DSColours.FeatureColours.MyFamily.Primary,
            isEditing = isEditing,
            isEnabled = true,
            onAddOrSaveClick = {
                viewModel.addOrEditEntry()
                viewModel.clearState()
                onBack()
            },
            onCancelClick = {
                viewModel.clearState()
                onBack()
            }
        )
    }
}

@Composable
fun ManageMyFamilyForm(
    draftEntry: Person,
    onFullNameChange: (String) -> Unit,
    onNickNameChange: (String) -> Unit,
    onRelationShipChange: (String) -> Unit,
    onBirthdaySelected: (LocalDate) -> Unit,
    onAddressChange: (String) -> Unit,
    onNumberChange: (String) -> Unit,
    onHobbiesChange: (String) -> Unit,
    onFavouriteColourChange: (String) -> Unit,
    onFavouriteBookChange: (String) -> Unit,
    onFavouriteFoodChange: (String) -> Unit,
    onFavouriteMusicChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        TextFieldForm(
            label = "Full Name",
            initialValue = draftEntry.fullName,
            onValueChange = onFullNameChange,
            colour = DSColours.FeatureColours.MyFamily.Primary
        )

        TextFieldForm(
            label = "Nickname",
            initialValue = draftEntry.nickName ?: "",
            onValueChange = onNickNameChange,
            colour = DSColours.FeatureColours.MyFamily.Primary
        )

        TextFieldForm(
            label = "Relationship",
            initialValue = draftEntry.relationShip,
            onValueChange = onRelationShipChange,
            colour = DSColours.FeatureColours.MyFamily.Primary
        )

        DatePickerForm(
            date = draftEntry.birthday ?: LocalDate.now(),
            onDateSelected = onBirthdaySelected,
            colour = DSColours.FeatureColours.MyFamily.Primary
        )

        TextFieldForm(
            label = "Address",
            initialValue = draftEntry.address ?: "",
            onValueChange = onAddressChange,
            colour = DSColours.FeatureColours.MyFamily.Primary
        )

        TextFieldForm(
            label = "Number",
            initialValue = draftEntry.number ?: "",
            onValueChange = onNumberChange,
            colour = DSColours.FeatureColours.MyFamily.Primary
        )

        TextFieldForm(
            label = "Hobbies",
            initialValue = draftEntry.hobbies ?: "",
            onValueChange = onHobbiesChange,
            colour = DSColours.FeatureColours.MyFamily.Primary
        )

        TextFieldForm(
            label = "Favourite Colour",
            initialValue = draftEntry.favouriteColour ?: "",
            onValueChange = onFavouriteColourChange,
            colour = DSColours.FeatureColours.MyFamily.Primary
        )

        TextFieldForm(
            label = "Favourite Book",
            initialValue = draftEntry.favouriteBook ?: "",
            onValueChange = onFavouriteBookChange,
            colour = DSColours.FeatureColours.MyFamily.Primary
        )

        TextFieldForm(
            label = "Favourite Food",
            initialValue = draftEntry.favouriteFood ?: "",
            onValueChange = onFavouriteFoodChange,
            colour = DSColours.FeatureColours.MyFamily.Primary
        )

        TextFieldForm(
            label = "Favourite Music",
            initialValue = draftEntry.favouriteMusic ?: "",
            onValueChange = onFavouriteMusicChange,
            colour = DSColours.FeatureColours.MyFamily.Primary
        )

    }
}