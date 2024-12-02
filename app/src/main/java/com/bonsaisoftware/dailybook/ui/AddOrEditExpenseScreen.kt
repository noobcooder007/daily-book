package com.bonsaisoftware.dailybook.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bonsaisoftware.dailybook.model.Expense
import com.bonsaisoftware.dailybook.model.ExpenseCategory
import com.bonsaisoftware.dailybook.ui.components.CustomTopBar
import com.bonsaisoftware.dailybook.ui.components.ExpenseForm
import java.util.Date

@Composable
fun AddOrEditExpenseScreen(
    expense: Expense? = null,
    onCanBackClick: Boolean = false,
    onBackClick: () -> Unit = {},
    onSaveChanges: (Expense) -> Unit = {}
) {
    Scaffold(
        topBar = {
            CustomTopBar(
                onCanBackClick = onCanBackClick,
                onBackClick = { onBackClick() },
                title = expense.let {
                    if (it == null) {
                        "Add expense"
                    } else {
                        "Edit expense"
                    }
                }
            )
        }
    ) {
        ExpenseForm(
            innerPadding = it,
            expense = expense,
            onSaveChanges = onSaveChanges
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditExpenseScreenPreview() {
    AddOrEditExpenseScreen(
        expense = Expense(
            expenseId = 1L,
            expenseAmount = 100,
            expenseName = "Test",
            expenseCategory = ExpenseCategory.FOOD,
            expenseDate = Date(),
            expenseIsActive = true
        ),
        onCanBackClick = true,
        onBackClick = {},
        onSaveChanges = {},
    )
}

@Preview(showBackground = true)
@Composable
fun AddExpenseScreenPreview() {
    AddOrEditExpenseScreen(
        expense = null,
        onCanBackClick = true,
        onBackClick = {},
        onSaveChanges = {},
    )
}
