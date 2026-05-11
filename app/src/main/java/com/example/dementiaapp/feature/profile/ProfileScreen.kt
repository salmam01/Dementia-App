package com.example.dementiaapp.feature.profile

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: ProfileViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ProfileCard(user = state.user)

        Spacer(modifier = Modifier.height(DSDimensions.Space5))

        ProfileItemsList(
            onItemClick = { }
        )
    }
}

@Composable
fun ProfileCard(
    user: User,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(DSColours.Tertiary)
            .padding(DSDimensions.Space4)
    ) {
        Image(
            painter = (user.image ?: painterResource(R.drawable.placeholder_avatar)) as Painter,
            contentDescription = null,
            modifier = Modifier
                .size(DSDimensions.Avatar2)
                .clip(CircleShape)
                .border(
                    width = DSDimensions.BorderRadius1,
                    color = DSColours.OnPrimary,
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
                text = user.name,
                fontSize = DSTypography.Headline.Large,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(DSDimensions.Space1))

            Text(
                text = "Me",
                fontSize = DSTypography.Headline.Large,
                fontWeight = FontWeight.Normal
            )
        }
    }
    HorizontalDivider(
        thickness = DSDimensions.DividerThickness2,
        color = DSColours.Divider
    )
}

@Composable
fun ProfileItemsList(
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(DSDimensions.Space4),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        ProfileItem(
            onItemClick = onItemClick
        )
        ProfileItem(
            onItemClick = onItemClick
        )
        ProfileItem(
            onItemClick = onItemClick
        )
    }
}

@Composable
fun ProfileItem(
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onItemClick,
        shape = RoundedCornerShape(DSDimensions.CornerRadius1),
        color = DSColours.Surface,
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
            Icon(
                imageVector = Icons.Rounded.CreditCard,
                contentDescription = null,
                tint = DSColours.Primary,
                modifier = Modifier
                    .size(DSDimensions.Icon7)
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
                    text = "My Data",
                    fontSize = DSTypography.Body.Large,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(DSDimensions.Space1))

                Text(
                    text = "Name, Address,...",
                    fontSize = DSTypography.Body.Medium,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start
                )
            }

            Spacer(modifier = Modifier.width(DSDimensions.Space4))

            Icon(
                imageVector = Icons.Rounded.ArrowBackIosNew,
                contentDescription = null,
                tint = DSColours.Primary,
                modifier = Modifier
                    .size(DSDimensions.Icon5)
                    .scale(scaleX = -1f, scaleY = 1f)
            )
        }
    }
}