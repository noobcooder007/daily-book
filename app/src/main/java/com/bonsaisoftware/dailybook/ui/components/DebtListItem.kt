package com.bonsaisoftware.dailybook.ui.components

import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.bonsaisoftware.dailybook.data.DebtManager
import com.bonsaisoftware.dailybook.model.Debt

@Composable
fun DebtListItem(debt: Debt) {
    ListItem(
        headlineContent = {
            Text(
                text = debt.debtName, style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            )
        },
        trailingContent = {
            Text(
                text = "$${debt.debtAmount}", style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
        },
        supportingContent = {
            Text(
                text = "${debt.expenseCategory.categoryName} - ${debt.creditCard.name}",
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
fun DebtListItemPreview() {
    DebtListItem(debt = DebtManager.debts[0])
}