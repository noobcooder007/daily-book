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
import com.bonsaisoftware.dailybook.model.CreditCard
import com.bonsaisoftware.dailybook.model.Debt
import com.bonsaisoftware.dailybook.presentation.DebtsUiState
import com.bonsaisoftware.dailybook.util.currencyFormat
import java.util.Date

@Composable
fun DebtsList(innerPadding: PaddingValues, uiState: DebtsUiState, onItemClick: (debtId: Long) -> Unit = {}) {
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
                    DebtListItem(debt = debt, onItemClick = onItemClick)
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
            debts = listOf(
                Debt(
                    debtId = 1,
                    debtName = "Comida",
                    debtAmount = 10000,
                    debtDate = Date(),
                    debtCreditCard = CreditCard.BBVA,
                    debtIsActive = true
                )
            ),
            total = 10000
        )
    )
}
