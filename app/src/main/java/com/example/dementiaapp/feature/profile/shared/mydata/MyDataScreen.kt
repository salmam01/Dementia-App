package com.example.dementiaapp.feature.profile.shared.mydata

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.dementiaapp.feature.profile.shared.ProfileViewModel
import com.example.dementiaapp.localization.LocalizedStrings
import com.example.dementiaapp.util.time.TimeFormatterUtil
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun MyDataScreen(
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: ProfileViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val user = state.user

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DSColours.Surface)
    ) {
        ProfileCard(
            name = user.name,
            description = "Me",
            image = user.image,

            canEdit = viewModel.hasAccessToAction(FeatureAction.EDIT),
            canDelete = false,

            onEditClick = onNavigate,
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
    val strings = LocalizedStrings.current
    val gender =
        if (user.gender == UserGender.FEMALE) strings.female
        else strings.male

    val age = user.birthday.let {
        LocalDate.now().year - it.year
    }.toString()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        DetailsRow(
            label = strings.fullName,
            detail = user.name
        )
        DetailsRow(
            label = strings.age,
            detail = age
        )
        DetailsRow(
            label = strings.birthday,
            detail = TimeFormatterUtil.formatBirthday(user.birthday)
        )
        DetailsRow(
            label = strings.gender,
            detail = gender
        )
        DetailsRow(
            label = strings.address,
            detail = user.address
        )
        DetailsRow(
            label = strings.number,
            detail = user.number
        )
        DetailsRow(
            label = strings.origin,
            detail = user.origin
        )
        DetailsRow(
            label = strings.birthplace,
            detail = user.birthPlace
        )
    }
}