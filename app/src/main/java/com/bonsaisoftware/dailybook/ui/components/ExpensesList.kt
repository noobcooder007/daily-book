package com.bonsaisoftware.dailybook.ui.components

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
import com.bonsaisoftware.dailybook.model.Expense
import com.bonsaisoftware.dailybook.model.ExpenseCategory
import com.bonsaisoftware.dailybook.presentation.ExpensesUiState
import com.bonsaisoftware.dailybook.util.currencyFormat
import java.util.Date

@Composable
fun ExpenseList(
    innerPadding: PaddingValues,
    uiState: ExpensesUiState,
    onItemClick: (expenseId: Long) -> Unit = {}
) {
    Column(
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            top = innerPadding.calculateTopPadding(),
            bottom = innerPadding.calculateBottomPadding()
        ),
    ) {
        SummaryCard(
            total = currencyFormat("${uiState.total}"),
            label = "Balance total",
            currency = "MXN",
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (uiState.expenses.isEmpty()) {
            EmptyList()
        } else {
            LazyColumn {
                items(uiState.expenses) { expense ->
                    ExpenseListItem(expense = expense, onItemClick = onItemClick)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpenseEmptyListPreview() {
    ExpenseList(
        innerPadding = PaddingValues(0.dp),
        uiState = ExpensesUiState(
            expenses = emptyList(),
            total = 0
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ExpenseListPreview() {
    ExpenseList(
        innerPadding = PaddingValues(0.dp),
        uiState = ExpensesUiState(
            expenses = listOf(
                Expense(
                    expenseId = 1,
                    expenseName = "Comida",
                    expenseAmount = -10000,
                    expenseDate = Date(),
                    expenseCategory = ExpenseCategory.FOOD,
                    expenseIsActive = true
                )
            ),
            total = -10000
        )
    )
}