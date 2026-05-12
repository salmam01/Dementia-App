package com.example.dementiaapp.feature.profile.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.R
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.domain.models.User
import com.example.dementiaapp.domain.models.UserRole
import com.example.dementiaapp.feature.components.ProfileCard
import com.example.dementiaapp.feature.profile.components.ProfileItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    onNavigateToMyData: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToCarePartner: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: ProfileViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val user = state.user

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ProfileCard(
            name = user.name,
            description = "Me",
            image = user.image,

            canEdit = false,
            canDelete = false,

            onEditClick = { },
            onDeleteClick = { },

            containerColour = DSColours.Tertiary,
            imageOutlineColour = DSColours.OnPrimary,
            dividerColour = DSColours.Divider
        )

        Spacer(modifier = Modifier.height(DSDimensions.Space5))

        ProfileItemsList(
            carePartner = state.carePartner,
            profileNavigation = state.profileNavigation,
            onItemClick = {
                when (it) {
                    ProfileNavigation.MY_DATA -> onNavigateToMyData()
                    ProfileNavigation.SETTINGS -> { }
                    ProfileNavigation.CARE_PARTNER -> onNavigateToCarePartner()
                }
            }
        )
    }
}

@Composable
fun ProfileItemsList(
    carePartner: User,
    profileNavigation: List<ProfileNavigation>,
    onItemClick: (ProfileNavigation) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(DSDimensions.Space5),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        profileNavigation.forEach {
            when (it) {
                ProfileNavigation.MY_DATA -> {
                    ProfileItem(
                        title = "My Data",
                        icon = Icons.Rounded.CreditCard,
                        description = "Name, Address, ...",
                        onItemClick = { onItemClick(it) },
                    )
                }
                ProfileNavigation.SETTINGS -> {
                    ProfileItem(
                        title = "Settings",
                        icon = Icons.Rounded.Settings,
                        description = "Change Appearance, ...",
                        onItemClick = { onItemClick(it) },
                    )
                }
                ProfileNavigation.CARE_PARTNER -> {
                    CarePartnerItem(
                        carePartner = carePartner,
                        onItemClick = { onItemClick(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun CarePartnerItem(
    carePartner: User,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val title = if (carePartner.role == UserRole.CARE_RECIPIENT) {
        "My Care Recipient"
    } else {
        "My Caregiver"
    }

    Surface(
        shape = RoundedCornerShape(DSDimensions.CornerRadius1),
        color = DSColours.FeatureColours.CarePartner.Primary,
        shadowElevation = 4.dp,
        tonalElevation = 0.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = DSDimensions.Space2)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(DSDimensions.Space4)
        ) {
            Image(
                painter = (carePartner.image
                    ?: painterResource(R.drawable.placeholder_avatar)) as Painter,
                contentDescription = "Care partner avatar",
                modifier = Modifier
                    .size(DSDimensions.Avatar1)
                    .clip(CircleShape)
                    .border(
                        width = DSDimensions.BorderRadius2,
                        color = DSColours.OnPrimary,
                        shape = CircleShape
                    )
            )

            Spacer(modifier = Modifier.width(DSDimensions.Space4))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    fontSize = DSTypography.Body.Large,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = DSColours.OnPrimary
                )

                Spacer(modifier = Modifier.height(DSDimensions.Space2))

                Text(
                    text = carePartner.name,
                    fontSize = DSTypography.Body.Medium,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start,
                    color = DSColours.OnPrimary
                )
            }

            Spacer(modifier = Modifier.width(DSDimensions.Space4))

            IconButton(
                onClick = onItemClick
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    contentDescription = null,
                    tint = DSColours.OnPrimary,
                    modifier = Modifier
                        .size(DSDimensions.Icon5)
                        .scale(scaleX = -1f, scaleY = 1f)
                )
            }
        }
    }
}