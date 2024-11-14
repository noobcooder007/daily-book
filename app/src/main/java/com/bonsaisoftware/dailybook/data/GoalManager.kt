package com.bonsaisoftware.dailybook.data

import com.bonsaisoftware.dailybook.model.Goal
import com.bonsaisoftware.dailybook.model.GoalStatus

object GoalManager {
    private val _goals = mutableListOf(
        Goal(
            goalId = 1,
            goalName = "Goal 1",
            goalAmount = 1000,
            goalDate = "2024-10-1",
            goalStatus = GoalStatus.PENDING
        )
    )
    val goals = _goals

    fun addGoal(goal: Goal) {
        _goals.add(goal)
    }

    fun editGoal(goal: Goal) {
        val index = goals.indexOfFirst {
            it.goalId == goal.goalId
        }
        if (index != -1) {
            _goals[index] = goals[index].copy(
                goalName = goal.goalName,
                goalAmount = goal.goalAmount,
                goalStatus = goal.goalStatus,
            )
        }
    }

}