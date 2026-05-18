package com.example.dementiaapp.feature.profile.carepartner

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.R
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.domain.models.User
import com.example.dementiaapp.domain.models.UserRole
import com.example.dementiaapp.feature.components.buttons.ActionButton
import com.example.dementiaapp.feature.components.buttons.ActionButtonStyles
import com.example.dementiaapp.feature.profile.main.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CarePartnerScreen(
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: ProfileViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val carePartner = state.carePartner

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if (carePartner != null) {
            CarePartnerHeader(carePartner)
            CarePartnerBody(carePartner)
            CarePartnerActions(
                onMessageClick = onNavigate,
                onCallClick = { }
            )
        }
    }
}

@Composable
fun CarePartnerHeader(
    carePartner: User,
    modifier: Modifier = Modifier
) {
    val description = if (carePartner.role == UserRole.CARE_RECIPIENT) {
        "My Care Recipient"
    } else {
        "My Caregiver"
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(DSDimensions.Space2),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(DSDimensions.Space4)
    ) {
        Image(
            painter = (carePartner.image ?: painterResource(R.drawable.placeholder_avatar)) as Painter,
            contentDescription = null,
            modifier = Modifier
                .size(DSDimensions.Avatar4)
                .clip(CircleShape)
                .border(
                    width = DSDimensions.BorderRadius2,
                    color = DSColours.FeatureColours.CarePartner.Outline,
                    shape = CircleShape
                )
        )

        Text(
            text = carePartner.name,
            fontSize = DSTypography.Display.Small,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = DSColours.OnSurface
        )

        Text(
            text = description,
            fontSize = DSTypography.Display.ExtraSmall,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            color = DSColours.OnSurface
        )
    }
}

@Composable
fun CarePartnerBody(
    carePartner: User,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(DSDimensions.Space2),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(DSDimensions.Space2)
    ) {
        CarePartnerDetail(
            icon = Icons.Rounded.Favorite,
            label = "Relationship",
            detail = "Daughter"
        )

        CarePartnerDetail(
            icon = Icons.Rounded.Phone,
            label = "Phone Number",
            detail = carePartner.number
        )
    }
}

@Composable
fun CarePartnerDetail(
    icon: ImageVector,
    label: String,
    detail: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = DSColours.FeatureColours.CarePartner.Primary,
                modifier = Modifier.size(DSDimensions.Icon4)
            )

            Spacer(modifier = Modifier.width(DSDimensions.Space2))

            Text(
                text = label,
                fontSize = DSTypography.Headline.Medium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = DSColours.OnSurface
            )
        }

        Spacer(modifier = Modifier.height(DSDimensions.Space2))

        Text(
            text = detail,
            fontSize = DSTypography.Body.Large,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            color = DSColours.OnSurface
        )
    }
}

@Composable
fun CarePartnerActions(
    onMessageClick: () -> Unit,
    onCallClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(DSDimensions.Space4)
    ) {
        ActionButton(
            style = ActionButtonStyles.Message,
            onMessageClick,

            fontSize = DSTypography.Headline.Large,
            iconSize = DSDimensions.Icon4,
            shape = RoundedCornerShape(DSDimensions.CornerRadius3),

            verticalPadding = DSDimensions.Space4,
            horizontalPadding = DSDimensions.Space5,
            contentSpacing = DSDimensions.Space2
        )

        Spacer(modifier = Modifier.height(DSDimensions.Space4))

        ActionButton(
            style = ActionButtonStyles.Call,
            onCallClick,

            fontSize = DSTypography.Headline.Large,
            iconSize = DSDimensions.Icon4,
            shape = RoundedCornerShape(DSDimensions.CornerRadius3),

            verticalPadding = DSDimensions.Space4,
            horizontalPadding = DSDimensions.Space5,
            contentSpacing = DSDimensions.Space2
        )
    }

}