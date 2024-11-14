package com.bonsaisoftware.dailybook.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.bonsaisoftware.dailybook.presentation.GoalsUiState
import com.bonsaisoftware.dailybook.ui.components.BagsList
import com.bonsaisoftware.dailybook.ui.components.CustomTopBar
import com.bonsaisoftware.dailybook.ui.components.FAB
import com.bonsaisoftware.dailybook.ui.components.GoalsList

@Composable
fun GoalsScreen(
    uiState: GoalsUiState,
    onCanBackClick: Boolean = false,
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CustomTopBar(
                onCanBackClick = onCanBackClick,
                onBackClick = onBackClick,
                title = "Goals"
            )
        },
        floatingActionButton = {
            FAB(icon = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add debt"
                )
            })
        }
    ) { innerPadding ->
        GoalsList(innerPadding = innerPadding, uiState = uiState)
    }
}