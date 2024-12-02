package com.bonsaisoftware.dailybook.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bonsaisoftware.dailybook.presentation.BagsViewModel
import com.bonsaisoftware.dailybook.presentation.DebtsViewModel
import com.bonsaisoftware.dailybook.presentation.ExpensesViewModel
import com.bonsaisoftware.dailybook.presentation.GoalsViewModel
import com.bonsaisoftware.dailybook.ui.AddOrEditDebtScreen
import com.bonsaisoftware.dailybook.ui.AddOrEditExpenseScreen
import com.bonsaisoftware.dailybook.ui.AddOrEditGoalScreen
import com.bonsaisoftware.dailybook.ui.BagsScreen
import com.bonsaisoftware.dailybook.ui.DebtScreen
import com.bonsaisoftware.dailybook.ui.ExpensesScreen
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
    val debtViewModel = koinViewModel<DebtsViewModel>()
    val debtUiState by debtViewModel.uiState.collectAsState()
    val bagsViewModel = koinViewModel<BagsViewModel>()
    val bagsUiState by bagsViewModel.uiState.collectAsState()
    val goalsViewModel = koinViewModel<GoalsViewModel>()
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
                    navController.navigate("/expense")
                }, onDebtClick = {
                    navController.navigate("/debt")
                }, onBagsClick = {
                    navController.navigate("/bag")
                }, onGoalsClick = {
                    navController.navigate("/goal")
                })
        }
        composable(
            route = "/expense",
            enterTransition = {
                when (initialState.destination.route) {
                    "/expense/{id}" ->
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Up,
                            animationSpec = tween(700)
                        )

                    else ->
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    "/expense/{id}" ->
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Down,
                            animationSpec = tween(700)
                        )

                    else ->
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(700)
                        )
                }
            }
        ) {
            ExpensesScreen(uiState = expenseUiState, onCanBackClick = true, onBackClick = {
                navController.popBackStack()
            }, onFabClick = { expenseId ->
                navController.navigate("/expense/$expenseId")
            }, onItemClick = { expenseId ->
                navController.navigate("/expense/$expenseId")
            })
        }
        composable(
            route = "/expense/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.LongType }
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(700)
                )
            }
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id")
            val expenseToAddOrEdit =
                id?.let { expenseId -> expensesViewModel.getExpenseWithID(id = expenseId) }
            AddOrEditExpenseScreen(
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
        composable(
            route = "/debt",
            enterTransition = {
                when (initialState.destination.route) {
                    "/debt/{id}" ->
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Up,
                            animationSpec = tween(700)
                        )

                    else ->
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    "/debt/{id}" ->
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Down,
                            animationSpec = tween(700)
                        )

                    else ->
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(700)
                        )
                }
            }
        ) {
            DebtScreen(uiState = debtUiState, onCanBackClick = true, onBackClick = {
                navController.popBackStack()
            }, onFabClick = { debtId ->
                navController.navigate("/debt/$debtId")
            }, onItemClick = { debtId ->
                navController.navigate("/debt/$debtId")
            })
        }
        composable(
            route = "/debt/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType }),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(700)
                )
            }
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id")
            val debtToAddOrEdit =
                id?.let { debtId -> debtViewModel.getDebtWithID(id = debtId) }
            AddOrEditDebtScreen(
                debt = debtToAddOrEdit,
                onCanBackClick = true,
                onBackClick = {
                    navController.popBackStack()
                }
            ) { debt ->
                if (debtToAddOrEdit == null) {
                    debtViewModel.addDebt(debt)
                } else {
                    debtViewModel.editDebt(debt)
                }
                navController.popBackStack()
            }
        }
        composable(route = "/bags") {
            BagsScreen(uiState = bagsUiState, onCanBackClick = true, onBackClick = {
                navController.popBackStack()
            })
        }
        composable(
            route = "/goal",
            enterTransition = {
                when (initialState.destination.route) {
                    "/goal/{id}" ->
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Up,
                            animationSpec = tween(700)
                        )
                    else ->
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    "/goal/{id}" ->
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Down,
                            animationSpec = tween(700)
                        )

                    else ->
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(700)
                        )
                }
            }
        ) {
            GoalsScreen(uiState = goalsUiState, onCanBackClick = true, onBackClick = {
                navController.popBackStack()
            }, onFabClick = { goalId ->
                navController.navigate("/goal/$goalId")
            }, onItemClick = { goalId ->
                navController.navigate("/goal/$goalId")
            })
        }
        composable(
            route = "/goal/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType }),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(700)
                )
            }
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id")
            val goalToAddOrEdit =
                id?.let { goalId -> goalsViewModel.getGoalWithID(id = goalId) }
            AddOrEditGoalScreen(onCanBackClick = true, onBackClick = {
                navController.popBackStack()
            }) { goal ->
                if (goalToAddOrEdit == null) {
                    goalsViewModel.addGoal(goal)
                } else {
                    goalsViewModel.editGoal(goal)
                }
                navController.popBackStack()
            }
        }
    }
}