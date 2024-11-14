package com.bonsaisoftware.dailybook.ui.components

import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.bonsaisoftware.dailybook.data.BagManager
import com.bonsaisoftware.dailybook.data.DebtManager
import com.bonsaisoftware.dailybook.data.GoalManager
import com.bonsaisoftware.dailybook.model.Bag
import com.bonsaisoftware.dailybook.model.Debt
import com.bonsaisoftware.dailybook.model.Goal

@Composable
fun GoalListItem(goal: Goal) {
    ListItem(
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
                text = "$${goal.goalAmount}", style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
        },
        supportingContent = {
//            Text(
//                text = "${debt.expenseCategory.categoryName} - ${debt.creditCard.name}",
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Normal,
//                )
//            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GoalListItemPreview() {
    GoalListItem(goal = GoalManager.goals[0])
}