package com.bonsaisoftware.dailybook.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.bonsaisoftware.dailybook.presentation.BagsUiState
import com.bonsaisoftware.dailybook.ui.components.BagsList
import com.bonsaisoftware.dailybook.ui.components.CustomTopBar
import com.bonsaisoftware.dailybook.ui.components.DebtsList
import com.bonsaisoftware.dailybook.ui.components.FAB

@Composable
fun BagsScreen(
    uiState: BagsUiState,
    onCanBackClick: Boolean = false,
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CustomTopBar(
                onCanBackClick = onCanBackClick,
                onBackClick = onBackClick,
                title = "Bags"
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
        BagsList(innerPadding = innerPadding, uiState = uiState)
    }
}