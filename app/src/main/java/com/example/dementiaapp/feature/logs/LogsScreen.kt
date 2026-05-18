package com.example.dementiaapp.feature.logs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.domain.models.feature.LogEntry
import com.example.dementiaapp.feature.logs.components.LogsItem
import com.example.dementiaapp.feature.logs.components.SummarySection
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LogsScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: LogsViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        SummarySection(
            extended = state.summaryExtended,
            onClick = { viewModel.toggleSummary() }
        )

        LogsList(logsList = state.logs)
    }
}

@Composable
fun LogsList(
    logsList: List<LogEntry>,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(DSDimensions.Space4),
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(DSDimensions.screenPadding)
    ) {
        logsList.forEach { log ->
            LogsItem(
                log
            )
        }
    }
}