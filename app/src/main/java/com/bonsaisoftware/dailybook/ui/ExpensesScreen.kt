package com.bonsaisoftware.dailybook.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bonsaisoftware.dailybook.model.Expense
import com.bonsaisoftware.dailybook.model.ExpenseCategory
import com.bonsaisoftware.dailybook.presentation.ExpensesUiState
import com.bonsaisoftware.dailybook.ui.components.CustomTopBar
import com.bonsaisoftware.dailybook.ui.components.ExpenseList
import com.bonsaisoftware.dailybook.ui.components.FAB
import java.util.Date

@Composable
fun ExpensesScreen(
    uiState: ExpensesUiState,
    onCanBackClick: Boolean = false,
    onBackClick: () -> Unit = {},
    onFabClick: (expenseId: Long) -> Unit = {},
    onItemClick: (expenseId: Long) -> Unit = {}
) {
    Scaffold(
        topBar = {
            CustomTopBar(
                onCanBackClick = onCanBackClick,
                onBackClick = onBackClick,
                title = "Balance"
            )
        },
        floatingActionButton = {
            FAB(icon = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add expense"
                )
            }, onClick = { onFabClick(-1L) })
        }
    ) { innerPadding ->
        ExpenseList(innerPadding = innerPadding, uiState = uiState, onItemClick = onItemClick)
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyBalanceScreenPreview() {
    ExpensesScreen(
        uiState = ExpensesUiState(
            expenses = emptyList(),
            total = 0
        ),
        onCanBackClick = true,
        onBackClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun BalanceScreenPreview() {
    ExpensesScreen(
        uiState = ExpensesUiState(
            expenses = listOf(
                Expense(
                    expenseId = 1,
                    expenseName = "Comida",
                    expenseAmount = -10000,
                    expenseDate = Date(),
                    expenseCategory = ExpenseCategory.FOOD,
                )
            ),
            total = -10000
        ),
        onCanBackClick = true,
        onBackClick = {}
    )
}