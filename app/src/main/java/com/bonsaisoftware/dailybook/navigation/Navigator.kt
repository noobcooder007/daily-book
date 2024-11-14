package com.bonsaisoftware.dailybook.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.NavType.Companion.LongType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bonsaisoftware.dailybook.data.BagManager
import com.bonsaisoftware.dailybook.data.BagRepoImpl
import com.bonsaisoftware.dailybook.data.DebtManager
import com.bonsaisoftware.dailybook.data.DebtRepoImpl
import com.bonsaisoftware.dailybook.data.GoalManager
import com.bonsaisoftware.dailybook.data.GoalRepoImpl
import com.bonsaisoftware.dailybook.presentation.BagsViewModel
import com.bonsaisoftware.dailybook.presentation.DebtsViewModel
import com.bonsaisoftware.dailybook.presentation.ExpensesViewModel
import com.bonsaisoftware.dailybook.presentation.GoalsViewModel
import com.bonsaisoftware.dailybook.ui.BagsScreen
import com.bonsaisoftware.dailybook.ui.BalanceScreen
import com.bonsaisoftware.dailybook.ui.DebtScreen
import com.bonsaisoftware.dailybook.ui.ExpenseScreen
import com.bonsaisoftware.dailybook.ui.GoalsScreen
import com.bonsaisoftware.dailybook.ui.HomeScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val expensesViewModel = koinViewModel<ExpensesViewModel>()
    val expenseUiState by expensesViewModel.uiState.collectAsState()
    val debtViewModel = viewModel {
        DebtsViewModel(DebtRepoImpl(DebtManager))
    }
    val debtUiState by debtViewModel.uiState.collectAsState()
    val bagsViewModel = viewModel {
        BagsViewModel(BagRepoImpl(BagManager))
    }
    val bagsUiState by bagsViewModel.uiState.collectAsState()
    val goalsViewModel = viewModel {
        GoalsViewModel(GoalRepoImpl(GoalManager))
    }
    val goalsUiState by goalsViewModel.uiState.collectAsState()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "/home"
    ) {
        composable(route = "/home") {
            HomeScreen(
                expenseUiState = expenseUiState,
                debtUiState = debtUiState,
                bagsUiState = bagsUiState,
                goalsUiState = goalsUiState,
                onBalanceClick = {
                    navController.navigate("/balance")
                }, onDebtClick = {
                    navController.navigate("/debt")
                }, onBagsClick = {
                    navController.navigate("/bags")
                }, onGoalsClick = {
                    navController.navigate("/goals")
                })
        }
        composable(route = "/balance") {
            BalanceScreen(uiState = expenseUiState, onCanBackClick = true, onBackClick = {
                navController.popBackStack()
            }, onFabClick = { expenseId ->
                navController.navigate("/expense/$expenseId")
            }, onItemClick = { expenseId ->
                navController.navigate("/expense/$expenseId")
            })
        }
        composable(route = "/expense/{id}", arguments = listOf(
            navArgument("id") { type = NavType.LongType }
        )) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id")
            val expenseToAddOrEdit =
                id?.let { expenseId -> expensesViewModel.getExpenseWithID(id = expenseId) }
            ExpenseScreen(
                expense = expenseToAddOrEdit,
                onCanBackClick = true,
                onBackClick = {
                    navController.popBackStack()
                }
            ) { expense ->
                if (expenseToAddOrEdit == null) {
                    expensesViewModel.addExpense(expense)
                } else {
                    expensesViewModel.editExpense(expense)
                }
                navController.popBackStack()
            }
        }
        composable(route = "/debt") {
            DebtScreen(uiState = debtUiState, onCanBackClick = true, onBackClick = {
                navController.popBackStack()
            })
        }
        composable(route = "/bags") {
            BagsScreen(uiState = bagsUiState, onCanBackClick = true, onBackClick = {
                navController.popBackStack()
            })
        }
        composable(route = "/goals") {
            GoalsScreen(uiState = goalsUiState, onCanBackClick = true, onBackClick = {
                navController.popBackStack()
            })
        }
    }
}