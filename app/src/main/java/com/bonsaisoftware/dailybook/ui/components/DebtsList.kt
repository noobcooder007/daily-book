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
import com.bonsaisoftware.dailybook.data.DebtManager
import com.bonsaisoftware.dailybook.presentation.DebtsUiState
import com.bonsaisoftware.dailybook.util.currencyFormat

@Composable
fun DebtsList(innerPadding: PaddingValues, uiState: DebtsUiState) {
    Column(
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            top = innerPadding.calculateTopPadding(),
            bottom = innerPadding.calculateBottomPadding()
        ),
    ) {
        SummaryCard(
            total = currencyFormat("${ uiState.total }"),
            label = "Balance total",
            currency = "MXN",
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (uiState.debts.isEmpty()) {
            EmptyList()
        } else {
            LazyColumn {
                items(uiState.debts) { debt ->
                    DebtListItem(debt = debt)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DebtsEmptyListPreview() {
    DebtsList(
        innerPadding = PaddingValues(0.dp),
        uiState = DebtsUiState(
            debts = emptyList(),
            total = 0
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DebtsListPreview() {
    DebtsList(
        innerPadding = PaddingValues(0.dp),
        uiState = DebtsUiState(
            debts = DebtManager.debts,
            total = DebtManager.debts.sumOf { it.debtAmount }
        )
    )
}
