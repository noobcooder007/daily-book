package com.bonsaisoftware.dailybook.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.bonsaisoftware.dailybook.model.Goal
import com.bonsaisoftware.dailybook.ui.components.CustomTopBar
import com.bonsaisoftware.dailybook.ui.components.GoalForm

@Composable
fun AddOrEditGoalScreen(
    goal: Goal? = null,
    onCanBackClick: Boolean = false,
    onBackClick: () -> Unit = {},
    onSaveChanges: (Goal) -> Unit = {}
) {
    Scaffold(
        topBar = {
            CustomTopBar(
                onCanBackClick = onCanBackClick,
                onBackClick = { onBackClick() },
                title = goal.let {
                    if (it == null) {
                        "Add goal"
                    } else {
                        "Edit goal"
                    }
                }
            )
        }
    ) {
        GoalForm(
            innerPadding = it,
            goal = goal,
            onSaveChanges = onSaveChanges
        )
    }
}