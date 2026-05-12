package com.example.dementiaapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.domain.models.FeatureType
import com.example.dementiaapp.feature.calendar.CalendarScreen
import com.example.dementiaapp.feature.chat.ChatScreen
import com.example.dementiaapp.feature.diary.DiaryScreen
import com.example.dementiaapp.feature.home.HomeScreen
import com.example.dementiaapp.feature.logs.LogsScreen
import com.example.dementiaapp.feature.medication.all.AllMedicationsScreen
import com.example.dementiaapp.feature.medication.management.ManageMedicationScreen
import com.example.dementiaapp.feature.medication.main.MedicationScreen
import com.example.dementiaapp.feature.myfamily.details.MyFamilyDetailScreen
import com.example.dementiaapp.feature.myfamily.main.MyFamilyScreen
import com.example.dementiaapp.feature.myfamily.manage.ManageMyFamilyScreen
import com.example.dementiaapp.feature.profile.carepartner.CarePartnerScreen
import com.example.dementiaapp.feature.profile.main.ProfileScreen
import com.example.dementiaapp.feature.profile.mydata.MyDataScreen
import com.example.dementiaapp.feature.reminders.carerecipient.CRRemindersScreen
import com.example.dementiaapp.feature.reminders.manage.ManageRemindersScreen
import com.example.dementiaapp.feature.reminders.shared.RemindersScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val navigationState = rememberNavigationState(
        startRoute = Route.Home,
        topLevelRoutes = TOP_LEVEL_DESTINATIONS.keys,
    )

    val navigator = remember {
        Navigator(navigationState)
    }
    val currentRoute = navigationState.currentRoute

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedKey = navigationState.topLevelRoute,
                onSelectKey = {
                    navigator.navigate(route = it)
                }
            )
        },
        topBar = {
            if (currentRoute != Route.Home) {
                val colours = TopAppBarDefaults.topAppBarColors(
                    containerColor = DSColours.Primary,
                    titleContentColor = DSColours.OnPrimary,
                    navigationIconContentColor = DSColours.OnPrimary)

                if (navigationState.isAtTopLevelRoot) {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = currentRoute.title(),
                                fontSize = DSTypography.Display.Small,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(
                                    horizontal = DSDimensions.Space4
                                )
                            )
                        },
                        colors = colours,
                        expandedHeight = DSDimensions.NavigationBarHeight,
                        modifier = Modifier
                            .shadow(
                                elevation = 4.dp,
                                shape = RectangleShape,
                                clip = false
                            )
                    )
                } else {
                    TopAppBar(
                        title = {
                            Text(
                                text = currentRoute.title(),
                                fontSize = DSTypography.Display.Small,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(
                                    horizontal = DSDimensions.Space4
                                )
                            )
                        },
                        colors = colours,
                        navigationIcon = {
                            IconButton(
                                onClick = navigator::goBack,
                                modifier = Modifier.padding(start = DSDimensions.Space2)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                    contentDescription = null,
                                    modifier = Modifier.size(DSDimensions.Icon6)
                                )
                            }
                        },
                        expandedHeight = DSDimensions.NavigationBarHeight,
                        modifier = Modifier
                            .shadow(
                                elevation = 4.dp,
                                shape = RectangleShape,
                                clip = false
                            )
                    )
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            onBack = navigator::goBack,
            entries = navigationState.toEntries(
                entryProvider {
                    entry<Route.Home> {
                        HomeScreen(
                            onNavigate = {
                                when (it) {
                                    FeatureType.CALENDAR -> navigator.navigate(Route.Calendar)
                                    FeatureType.DIARY -> navigator.navigate(Route.Diary)
                                    FeatureType.MY_FAMILY -> navigator.navigate(Route.MyFamily)
                                    FeatureType.MEDICATION -> navigator.navigate(Route.Medication)
                                    FeatureType.REMINDERS -> navigator.navigate(Route.Reminders)
                                    FeatureType.LOGS -> navigator.navigate(Route.Logs)
                                    else -> error("Unknown Route")
                                }
                            }
                        )
                    }
                    entry<Route.Calendar> {
                        CalendarScreen(
                            onNavigate = {
                                when (it) {
                                    FeatureType.DIARY -> navigator.navigate(Route.Diary)
                                    FeatureType.MEDICATION -> navigator.navigate(Route.Medication)
                                    FeatureType.REMINDERS -> navigator.navigate(Route.Reminders)
                                    else -> { }
                                }
                            }
                        )
                    }
                    entry<Route.Diary> {
                        DiaryScreen()
                    }
                    entry<Route.MyFamily> {
                        MyFamilyScreen(
                            onNavigate = {
                                navigator.navigate(Route.MyFamilyDetails)
                            },
                            onAddEntry = {
                                navigator.navigate(
                                    Route.ManageMyFamily(selectedEntryId = null)
                                )
                            }
                        )
                    }
                    entry<Route.MyFamilyDetails> {
                        MyFamilyDetailScreen(
                            onReturn = {
                                navigator.navigate(Route.MyFamily)
                            },
                            onEditEntry = {
                                navigator.navigate(
                                    Route.ManageMyFamily(selectedEntryId = it)
                                )
                            }
                        )
                    }
                    entry<Route.ManageMyFamily> {
                        ManageMyFamilyScreen(
                            selectedEntryId = it.selectedEntryId,
                            onBack = {
                                navigator.navigate(Route.MyFamily)
                            }
                        )
                    }
                    entry<Route.Medication> {
                        MedicationScreen(
                            onNavigate = {
                                navigator.navigate(Route.AllMedications)
                            }
                        )
                    }
                    entry<Route.AllMedications> {
                        AllMedicationsScreen(
                            onAddOrEditMedication = {
                                navigator.navigate(
                                    Route.ManageMedication(selectedMedicationId = it)
                                )
                            },
                        )
                    }
                    entry<Route.ManageMedication> {
                        ManageMedicationScreen(selectedMedicationId = it.selectedMedicationId)
                    }
                    entry<Route.Reminders> {
                        RemindersScreen(
                            onAddOrEditReminder = {
                                navigator.navigate(
                                    Route.ManageReminders(selectedReminderId = it)
                                )
                            }
                        )
                    }
                    entry<Route.ManageReminders> {
                        ManageRemindersScreen(
                            selectedReminderId = it.selectedReminderId,
                            onBack = navigator::goBack

                        )
                    }
                    entry<Route.Logs> {
                        LogsScreen()
                    }
                    entry<Route.Chat> {
                        ChatScreen()
                    }
                    entry<Route.Profile> {
                        ProfileScreen(
                            onNavigateToMyData = {
                                navigator.navigate(Route.MyData)
                            },
                            onNavigateToSettings = { },
                            onNavigateToCarePartner = {
                                navigator.navigate(Route.CarePartner)
                            }
                        )
                    }
                    entry<Route.CarePartner> {
                        CarePartnerScreen(
                            onNavigate = { }
                        )
                    }
                    entry<Route.MyData> {
                        MyDataScreen()
                    }
                }
            )
        )
    }
}