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
import androidx.navigation.compose.rememberNavController
import com.bonsaisoftware.dailybook.data.BagManager
import com.bonsaisoftware.dailybook.data.DebtManager
import com.bonsaisoftware.dailybook.data.GoalManager
import com.bonsaisoftware.dailybook.presentation.BagsUiState
import com.bonsaisoftware.dailybook.presentation.DebtsUiState
import com.bonsaisoftware.dailybook.presentation.ExpensesUiState
import com.bonsaisoftware.dailybook.presentation.GoalsUiState
import com.bonsaisoftware.dailybook.ui.components.SummaryCard
import com.bonsaisoftware.dailybook.util.currencyFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
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
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()
            )
        ) {
            SummaryCard(total = currencyFormat("${expenseUiState.total}"),
                label = "Balance total",
                currency = "MXN",
                onClick = { onBalanceClick() })
            Spacer(modifier = Modifier.height(8.dp))
            SummaryCard(
                total = currencyFormat("${debtUiState.total}"),
                label = "Deuda",
                currency = "MXN",
                onClick = { onDebtClick() })
            Spacer(modifier = Modifier.height(8.dp))
            SummaryCard(
                total = currencyFormat("${bagsUiState.total}"),
                label = "Apartados",
                currency = "MXN",
                onClick = { onBagsClick() })
            Spacer(modifier = Modifier.height(8.dp))
            SummaryCard(
                total = currencyFormat("${goalsUiState.total}"),
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
        Modifier.padding(
            start = 16.dp,
            end = 16.dp,
        ),
        expenseUiState = ExpensesUiState(
            expenses = emptyList(),
            total = 10000,
        ),
        debtUiState = DebtsUiState(
            debts = DebtManager.debts,
            total = DebtManager.debts.sumOf { it.debtAmount },
        ),
        bagsUiState = BagsUiState(
            bags = BagManager.bags,
            total = BagManager.bags.sumOf { it.bagAmount },
        ),
        goalsUiState = GoalsUiState(
            goals = GoalManager.goals,
            total = GoalManager.goals.sumOf { it.goalAmount },
        ),
    )
}
