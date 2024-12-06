package com.bonsaisoftware.dailybook.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bonsaisoftware.dailybook.model.Goal
import com.bonsaisoftware.dailybook.model.GoalStatus
import com.bonsaisoftware.dailybook.presentation.GoalsUiState
import com.bonsaisoftware.dailybook.util.currencyFormat
import java.util.Date

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GoalsList(
    innerPadding: PaddingValues,
    uiState: GoalsUiState,
    onItemClick: (goalId: Long) -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    with(sharedTransitionScope) {
        Column(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()
            )
        ) {
            SummaryCard(
                modifier = Modifier.Companion.sharedElement(
                    rememberSharedContentState(key = "summaryGoals"),
                    animatedVisibilityScope
                ),
                total = currencyFormat(uiState.total.toBigDecimal()),
                label = "Total de metas",
                currency = "MXN",
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (uiState.goals.isEmpty()) {
                EmptyList()
            } else {
                LazyColumn {
                    items(uiState.goals) { goal ->
                        GoalListItem(goal = goal, onItemClick = onItemClick)
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GoalsEmptyListPreview() {
//    GoalsList(
//        innerPadding = PaddingValues(0.dp),
//        uiState = GoalsUiState(
//            goals = emptyList(),
//            total = 0
//        ),
//        onItemClick = {}
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun GoalsListPreview() {
//    GoalsList(
//        innerPadding = PaddingValues(0.dp),
//        uiState = GoalsUiState(
//            goals = listOf(
//                Goal(
//                    goalId = 1,
//                    goalName = "Goal 1",
//                    goalAmount = 100000,
//                    goalDate = Date(),
//                    goalStatus = GoalStatus.PENDING,
//                    goalIsActive = true,
//                ),
//            ),
//            total = 100000
//        ),
//        onItemClick = {}
//    )
//}
