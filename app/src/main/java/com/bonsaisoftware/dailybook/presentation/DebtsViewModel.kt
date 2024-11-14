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

class DebtsViewModel(private val repo: DebtRepository): ViewModel() {
    private val _uiState = MutableStateFlow(DebtsUiState())
    val uiState = _uiState.asStateFlow()
    private val allDebts = repo.getDebts()

    init {
        getAllDebts()
    }

    private fun updateState() {
        _uiState.update { state ->
            state.copy(debts = allDebts, total = allDebts.sumOf { it.debtAmount })
        }
    }

    fun getAllDebts() {
        viewModelScope.launch {
            updateState()
        }
    }

    fun addDebt(debt: Debt) {
        viewModelScope.launch {
            repo.addDebt(debt)
            updateState()
        }
    }

    fun editDebt(debt: Debt) {
        viewModelScope.launch {
            repo.editDebt(debt)
            updateState()
        }
    }

    fun getDebtWithID(id: Long): Debt {
        return allDebts.first { it.debtId == id }
    }


}