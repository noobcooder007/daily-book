package com.bonsaisoftware.dailybook.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonsaisoftware.dailybook.domain.DebtRepository
import com.bonsaisoftware.dailybook.model.Debt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DebtsUiState(
    val debts: List<Debt> = emptyList(),
    val total: Long = 0
)

class DebtsViewModel(private val repo: DebtRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(DebtsUiState())
    val uiState = _uiState.asStateFlow()
    private var allDebts = mutableListOf<Debt>()

    init {
        getAllDebts()
    }

    private fun updateDebtsList() {
        viewModelScope.launch {
            allDebts = repo.getDebts().toMutableList()
            updateState()
        }
    }

    private fun updateState() {
        _uiState.update { state ->
            state.copy(
                debts = allDebts,
                total = allDebts.sumOf { it.debtAmount }
            )
        }
    }

    fun getAllDebts() {
        repo.getDebts()
        updateDebtsList()
    }

    fun addDebt(debt: Debt) {
        repo.addDebt(debt)
        updateDebtsList()
    }

    fun editDebt(debt: Debt) {
        repo.editDebt(debt)
        updateDebtsList()
    }

    fun getDebtWithID(id: Long): Debt? {
        return repo.getDebtWithID(id)
    }

}