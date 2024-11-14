package com.bonsaisoftware.dailybook.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonsaisoftware.dailybook.domain.GoalRepository
import com.bonsaisoftware.dailybook.model.Goal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class GoalsUiState(
    val goals: List<Goal> = emptyList(),
    val total: Long = 0
)

class GoalsViewModel(private val repo: GoalRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(GoalsUiState())
    val uiState = _uiState.asStateFlow()
    private val allGoals = repo.getAllGoals()

    init {
        getAllGoals()
    }

    private fun updateState() {
        _uiState.update { state ->
            state.copy(goals = allGoals, total = allGoals.sumOf { it.goalAmount })
        }
    }

    private fun getAllGoals() {
        viewModelScope.launch {
            updateState()
        }
    }

    fun addGoal(goal: Goal) {
        viewModelScope.launch {
            repo.addGoal(goal)
            updateState()
        }
    }

    fun editGoal(goal: Goal) {
        viewModelScope.launch {
            repo.editGoal(goal)
            updateState()
        }
    }

}