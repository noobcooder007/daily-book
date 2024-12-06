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
import com.bonsaisoftware.dailybook.model.Bag
import com.bonsaisoftware.dailybook.model.Goal
import com.bonsaisoftware.dailybook.model.GoalStatus
import java.util.Date

@Composable
fun BagListItem(bag: Bag, onItemClick: (bagId: Long) -> Unit) {
    ListItem(modifier = Modifier.clickable { onItemClick(bag.bagId) },
        headlineContent = {
            Text(
                text = bag.bagName, style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            )
        },
        trailingContent = {
            Text(
                text = "$${bag.bagAmount}", style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
        },
        supportingContent = {
            if (bag.goal == null) {
                Text(
                    text = "No goal assigned", style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                    )
                )
            } else {
//            TODO: Add goal percent
                Text(
                    text = "${bag.goal.goalAmount / bag.bagAmount * 100} of ${bag.goal.goalAmount}% completed",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                    )
                )
            }
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//fun BagListItemWithGoalNotAssignedPreview() {
//    BagListItem(
//        bag = Bag(
//            bagId = 1,
//            bagName = "Bag 1",
//            bagAmount = 100,
//            bagDate = Date(),
//            bagIsActive = true,
//            goal = null,
//        ),
//        onItemClick = onItemClick
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun BagListItemWithGoalAssignedPreview() {
//    BagListItem(
//        bag = Bag(
//            bagId = 1,
//            bagName = "Bag 1",
//            bagAmount = 100,
//            bagDate = Date(),
//            bagIsActive = true,
//            goal = Goal(
//                goalId = 1,
//                goalName = "Goal 1",
//                goalAmount = 100,
//                goalDate = Date(),
//                goalStatus = GoalStatus.PENDING,
//                goalIsActive = true,
//            )
//        ),
//        onItemClick = onItemClick
//    )
//}
