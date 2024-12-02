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
    private var allGoals = mutableListOf<Goal>()

    init {
        getAllGoals()
    }

    private fun updateGoalsList() {
        viewModelScope.launch {
            allGoals = repo.getAllGoals().toMutableList()
            updateState()
        }
    }

    private fun updateState() {
        _uiState.update { state ->
            state.copy(goals = allGoals, total = allGoals.sumOf { it.goalAmount })
        }
    }

    private fun getAllGoals() {
        repo.getAllGoals()
        updateGoalsList()
    }

    fun addGoal(goal: Goal) {
        repo.addGoal(goal)
        updateGoalsList()
    }

    fun editGoal(goal: Goal) {
        repo.editGoal(goal)
        updateGoalsList()
    }

    fun deleteGoal(goal: Goal) {
        repo.deleteGoal(goal)
        updateGoalsList()
    }

    fun getGoalWithID(id: Long): Goal? {
        return repo.getGoalWithID(id)
    }


}