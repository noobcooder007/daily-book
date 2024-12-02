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
import com.bonsaisoftware.dailybook.model.Expense
import com.bonsaisoftware.dailybook.model.ExpenseCategory
import com.bonsaisoftware.dailybook.util.currencyFormat
import java.util.Date

@Composable
fun ExpenseListItem(expense: Expense, onItemClick: (expenseId: Long) -> Unit = {}) {
    ListItem(modifier = Modifier.clickable { onItemClick(expense.expenseId) }, headlineContent = {
        Text(
            text = expense.expenseName, style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )
        )
    }, trailingContent = {
        Text(
            text = currencyFormat(expense.expenseAmount.toBigDecimal()), style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        )
    }, supportingContent = {
        Text(
            text = expense.expenseCategory.categoryName, style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
            )
        )
    })
}

@Preview(showBackground = true)
@Composable
fun ExpenseListItemPreview() {
    ExpenseListItem(
        expense = Expense(
            expenseId = 1,
            expenseName = "Comida",
            expenseAmount = 10000,
            expenseDate = Date(),
            expenseCategory = ExpenseCategory.FOOD,
            expenseIsActive = true
        )
    )
}
