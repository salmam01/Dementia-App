package com.example.dementiaapp.feature.profile.mydata

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
import com.example.dementiaapp.domain.models.User
import com.example.dementiaapp.domain.models.UserGender
import com.example.dementiaapp.feature.components.DetailsRow
import com.example.dementiaapp.feature.components.ProfileCard
import com.example.dementiaapp.feature.profile.main.ProfileViewModel
import com.example.dementiaapp.util.time.TimeFormatterUtil
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun MyDataScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: ProfileViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val user = state.user

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        ProfileCard(
            name = user.name,
            description = "Me",
            image = user.image,

            canEdit = viewModel.hasAccessToAction(FeatureAction.EDIT),
            canDelete = viewModel.hasAccessToAction(FeatureAction.DELETE),

            onEditClick = { },
            onDeleteClick = { },
        )

        MyDataDetails(user)
    }
}

@Composable
fun MyDataDetails(
    user: User,
    modifier: Modifier = Modifier
) {
    val gender = if (user.gender == UserGender.FEMALE) {
        "Female"
    } else {
        "Male"
    }

    val age = user.birthday.let {
        LocalDate.now().year - it.year
    }.toString()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        DetailsRow(
            label = "Full Name",
            detail = user.name
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
            label = "Birthday",
            detail = TimeFormatterUtil.formatBirthday(user.birthday)
        )
        HorizontalDivider(
            thickness = DSDimensions.DividerThickness1,
            color = DSColours.Divider
        )

        DetailsRow(
            label = "Sex",
            detail = gender
        )
        HorizontalDivider(
            thickness = DSDimensions.DividerThickness1,
            color = DSColours.Divider
        )

        DetailsRow(
            label = "Address",
            detail = user.address
        )
        HorizontalDivider(
            thickness = DSDimensions.DividerThickness1,
            color = DSColours.Divider
        )

        DetailsRow(
            label = "Number",
            detail = user.number
        )
        HorizontalDivider(
            thickness = DSDimensions.DividerThickness1,
            color = DSColours.Divider
        )

        DetailsRow(
            label = "Origin",
            detail = user.origin
        )
        HorizontalDivider(
            thickness = DSDimensions.DividerThickness1,
            color = DSColours.Divider
        )

        DetailsRow(
            label = "Birthplace",
            detail = user.birthPlace
        )
        HorizontalDivider(
            thickness = DSDimensions.DividerThickness1,
            color = DSColours.Divider
        )
    }
}