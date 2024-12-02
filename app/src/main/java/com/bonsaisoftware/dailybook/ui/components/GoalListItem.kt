package com.bonsaisoftware.dailybook.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.bonsaisoftware.dailybook.model.Goal
import com.bonsaisoftware.dailybook.model.GoalStatus
import com.bonsaisoftware.dailybook.util.currencyFormat
import java.util.Date

@Composable
fun GoalListItem(goal: Goal, onItemClick: (goalId: Long) -> Unit = {}) {
    ListItem(
        modifier = Modifier.clickable { onItemClick(goal.goalId) },
        headlineContent = {
            Text(
                text = goal.goalName, style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            )
        },
        trailingContent = {
            Text(
                text = currencyFormat(goal.goalAmount.toBigDecimal()), style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
        },
        supportingContent = {
            Text(
                text = goal.goalStatus.name,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                )
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GoalListItemPreview() {
    GoalListItem(
        goal = Goal(
            goalId = 1,
            goalName = "Goal 1",
            goalAmount = 100000,
            goalDate = Date(),
            goalStatus = GoalStatus.PENDING,
            goalIsActive = true,
        ),
        onItemClick = {}
    )
}
