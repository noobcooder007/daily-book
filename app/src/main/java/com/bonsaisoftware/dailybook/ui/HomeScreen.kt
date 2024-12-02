package com.bonsaisoftware.dailybook.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bonsaisoftware.dailybook.presentation.BagsUiState
import com.bonsaisoftware.dailybook.presentation.DebtsUiState
import com.bonsaisoftware.dailybook.presentation.ExpensesUiState
import com.bonsaisoftware.dailybook.presentation.GoalsUiState
import com.bonsaisoftware.dailybook.ui.components.SummaryCard
import com.bonsaisoftware.dailybook.util.currencyFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    expenseUiState: ExpensesUiState,
    debtUiState: DebtsUiState,
    bagsUiState: BagsUiState,
    goalsUiState: GoalsUiState,
    onBalanceClick: () -> Unit = {},
    onDebtClick: () -> Unit = {},
    onBagsClick: () -> Unit = {},
    onGoalsClick: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Welcome",
                    )
                },
            )
        },
    ) {
        Column(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = it.calculateTopPadding(),
                bottom = it.calculateBottomPadding()
            )
        ) {
            SummaryCard(total = currencyFormat(expenseUiState.total.toBigDecimal()),
                label = "Balance total",
                currency = "MXN",
                onClick = { onBalanceClick() })
            Spacer(modifier = Modifier.height(8.dp))
            SummaryCard(
                total = currencyFormat(debtUiState.total.toBigDecimal()),
                label = "Deuda",
                currency = "MXN",
                onClick = { onDebtClick() })
            Spacer(modifier = Modifier.height(8.dp))
            SummaryCard(
                total = currencyFormat(bagsUiState.total.toBigDecimal()),
                label = "Apartados",
                currency = "MXN",
                onClick = { onBagsClick() })
            Spacer(modifier = Modifier.height(8.dp))
            SummaryCard(
                total = currencyFormat(goalsUiState.total.toBigDecimal()),
                label = "Metas",
                currency = "MXN",
                onClick = {
                    onGoalsClick()
                })
        }
    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        expenseUiState = ExpensesUiState(
            expenses = emptyList(),
            total = 1000000,
        ),
        debtUiState = DebtsUiState(
            debts = emptyList(),
            total = 700000,
        ),
        bagsUiState = BagsUiState(
            bags = emptyList(),
            total = 200000,
        ),
        goalsUiState = GoalsUiState(
            goals = emptyList(),
            total = 350000,
        ),
    )
}
