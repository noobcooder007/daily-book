package com.bonsaisoftware.dailybook.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonsaisoftware.dailybook.domain.ExpenseRepository
import com.bonsaisoftware.dailybook.model.Expense
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ExpensesUiState(
    val expenses: List<Expense> = emptyList(),
    val total: Long = 0
)

class ExpensesViewModel(private val repo: ExpenseRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(ExpensesUiState())
    val uiState = _uiState.asStateFlow()
    private var allExpenses = mutableListOf<Expense>()

    init {
        getAllExpenses()
    }

    private fun updateExpensesList() {
        viewModelScope.launch {
            allExpenses = repo.getAllExpenses().toMutableList()
            updateState()
        }
    }

    private fun updateState() {
        _uiState.update { state ->
            state.copy(
                expenses = allExpenses,
                total = allExpenses.sumOf { it.expenseAmount }
            )
        }
    }

    private fun getAllExpenses() {
        repo.getAllExpenses()
        updateExpensesList()
    }

    fun addExpense(expense: Expense, expenseCanEdit: Boolean) {
        repo.addExpense(expense, expenseCanEdit)
        updateExpensesList()
    }

    fun editExpense(expense: Expense) {
        repo.editExpense(expense)
        updateExpensesList()
    }

    fun getExpenseWithID(id: Long): Expense? {
        return repo.getExpenseWithID(id)
    }
}