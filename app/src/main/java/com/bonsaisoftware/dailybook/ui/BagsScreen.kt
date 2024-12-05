package com.bonsaisoftware.dailybook.ui

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.bonsaisoftware.dailybook.presentation.BagsUiState
import com.bonsaisoftware.dailybook.ui.components.BagsList
import com.bonsaisoftware.dailybook.ui.components.CustomTopBar
import com.bonsaisoftware.dailybook.ui.components.FAB

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BagsScreen(
    uiState: BagsUiState,
    onCanBackClick: Boolean = false,
    onBackClick: () -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
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
    ) {
        BagsList(
            innerPadding = it,
            uiState = uiState,
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope
        )
    }
}