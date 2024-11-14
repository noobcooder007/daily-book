package com.bonsaisoftware.dailybook.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.bonsaisoftware.dailybook.presentation.DebtsUiState
import com.bonsaisoftware.dailybook.ui.components.CustomTopBar
import com.bonsaisoftware.dailybook.ui.components.DebtsList
import com.bonsaisoftware.dailybook.ui.components.FAB

@Composable
fun DebtScreen(
    uiState: DebtsUiState,
    onCanBackClick: Boolean = false,
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CustomTopBar(
                onCanBackClick = onCanBackClick,
                onBackClick = onBackClick,
                title = "Debts"
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
        DebtsList(innerPadding = innerPadding, uiState = uiState)
    }
}