package com.example.dementiaapp.feature.profile.shared.mydata

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person2
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.domain.models.User
import com.example.dementiaapp.domain.models.UserGender
import com.example.dementiaapp.feature.components.ProfileCard
import com.example.dementiaapp.feature.components.buttons.FormButtons
import com.example.dementiaapp.feature.components.form.DatePickerForm
import com.example.dementiaapp.feature.components.form.DropDownForm
import com.example.dementiaapp.feature.components.form.TextFieldForm
import com.example.dementiaapp.localization.LocalizedStrings
import org.koin.compose.viewmodel.koinViewModel
import java.time.LocalDate

@Composable
fun MyDataFormScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: MyDataFormViewModel = koinViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    val strings = LocalizedStrings.current

    LaunchedEffect(Unit) {
        viewModel.getUser()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DSColours.Surface)
    ) {
        ProfileCard(
            name = "",
            description = "",
            image = null,
            isEditing = true,

            canEdit = false,
            canDelete = false,

            onEditClick = { },
            onDeleteClick = { },
            onChangePhoto = { },
            onRemovePhoto = { }
        )

        MyDataFormContent(
            draftUser = state.value.draftUser,
            onNameChange = { viewModel.setName(it) },
            onGenderChange = { viewModel.setGender(it) },
            onBirthdaySelected = { viewModel.setBirthday(it) },
            onAddressChange = { viewModel.setAddress(it) },
            onNumberChange = { viewModel.setNumber(it) },
            onOriginChange = { viewModel.setOrigin(it) },
            onBirthPlaceChange = { viewModel.setBirthPlace(it) },
            modifier = Modifier.weight(1f)
        )

        FormButtons(
            colour = DSColours.Primary,
            isEditing = state.value.isEditing,
            isEnabled = state.value.isValid,
            onAddOrSaveClick = {
                viewModel.updateUser()
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
fun MyDataFormContent(
    draftUser: User,
    onNameChange: (String) -> Unit,
    onGenderChange: (UserGender) -> Unit,
    onBirthdaySelected: (LocalDate) -> Unit,
    onAddressChange: (String) -> Unit,
    onNumberChange: (String) -> Unit,
    onOriginChange: (String) -> Unit,
    onBirthPlaceChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val strings = LocalizedStrings.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        TextFieldForm(
            label = strings.name,
            initialValue = draftUser.name,
            onValueChange = onNameChange,
            colour = DSColours.FeatureColours.MyFamily.Primary
        )
        DropDownForm(
            options = UserGender.entries,
            selectedOption = draftUser.gender,
            label = strings.gender,
            colour = DSColours.Primary,
            optionText = { it.name },
            onOptionSelected = { onGenderChange(it) },
            leadingIcon = Icons.Rounded.Person2
        )
        DatePickerForm(
            date = draftUser.birthday,
            onDateSelected = onBirthdaySelected,
            label = strings.birthday,
            colour = DSColours.Primary
        )
        TextFieldForm(
            label = strings.address,
            initialValue = draftUser.address,
            onValueChange = onAddressChange,
            colour = DSColours.Primary
        )
        TextFieldForm(
            label = strings.number,
            initialValue = draftUser.number,
            onValueChange = onNumberChange,
            colour = DSColours.Primary
        )
        TextFieldForm(
            label = strings.origin,
            initialValue = draftUser.origin,
            onValueChange = onOriginChange,
            colour = DSColours.Primary
        )
        TextFieldForm(
            label = strings.birthplace,
            initialValue = draftUser.birthPlace,
            onValueChange = onBirthPlaceChange,
            colour = DSColours.Primary
        )
    }
}