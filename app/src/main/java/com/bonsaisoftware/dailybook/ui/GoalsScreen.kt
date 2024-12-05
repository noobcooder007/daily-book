package com.bonsaisoftware.dailybook.ui

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.bonsaisoftware.dailybook.presentation.GoalsUiState
import com.bonsaisoftware.dailybook.ui.components.CustomTopBar
import com.bonsaisoftware.dailybook.ui.components.FAB
import com.bonsaisoftware.dailybook.ui.components.GoalsList

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GoalsScreen(
    uiState: GoalsUiState,
    onCanBackClick: Boolean = false,
    onBackClick: () -> Unit = {},
    onFabClick: (goalId: Long) -> Unit = {},
    onItemClick: (goalId: Long) -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
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
                    contentDescription = "Add goal"
                )
            }, onClick = { onFabClick(-1L) })
        }
    ) {
        GoalsList(
            innerPadding = it,
            uiState = uiState,
            onItemClick = onItemClick,
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope
        )
    }
}