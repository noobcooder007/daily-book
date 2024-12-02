package com.bonsaisoftware.dailybook.domain

import com.bonsaisoftware.dailybook.model.Goal

interface GoalRepository {
    fun getAllGoals(): List<Goal>
    fun addGoal(goal: Goal)
    fun editGoal(goal: Goal)
    fun deleteGoal(goal: Goal)
    fun getGoalWithID(id: Long): Goal?
}